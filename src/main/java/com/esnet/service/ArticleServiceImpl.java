package com.esnet.service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.esnet.beans.Article;
import com.esnet.beans.Stock;
import com.esnet.repository.ArticleRepository;
import com.esnet.repository.StockRepository;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
    private ArticleRepository articleRepository;
	@Autowired
    private StockRepository stockRepository;

    
    @Override
    public Article save(Article article) {
        
        if (article.getCode() == null ||
            article.getCode().trim().isEmpty()) {

            throw new RuntimeException(
                    "Le code de l'article est obligatoire");
        }

        if (articleRepository.existsByCode(article.getCode())) {
            throw new RuntimeException(
                    "Un article existe déjà avec le code : "
                    + article.getCode());
        }

        if (article.getNom() == null ||
            article.getNom().trim().isEmpty()) {

            throw new RuntimeException(
                    "Le nom de l'article est obligatoire");
        }

        if (article.getQuantite() == 0) {
            article.setQuantite(0);
        }

        if (article.getQuantite() < 0) {
            throw new RuntimeException(
                    "La quantité ne peut pas être négative");
        }

        if (article.getDateCreation() == null) {
            article.setDateCreation(LocalDateTime.now());
        }

        /*
         * Vérification du stock
         */
        if (article.getStock() != null) {

            Long stockId = article.getStock().getId();

            Stock stock = stockRepository.findById(stockId)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Stock introuvable avec l'id : "
                            + stockId));

            article.setStock(stock);
        }

        return articleRepository.save(article);
    }

    @Override
    public Article update(Long id, Article article) {

        Article existant = findById(id);

        if (article.getNom() == null ||
            article.getNom().trim().isEmpty()) {

            throw new RuntimeException(
                    "Le nom de l'article est obligatoire");
        }

        existant.setCode(article.getCode());
        existant.setNom(article.getNom());
        existant.setDescription(article.getDescription());

        /*
         * On ne modifie PAS la quantité ici.
         *
         * La quantité doit être modifiée
         * par MouvementStockService.
         */

        if (article.getStock() != null) {

            Long stockId = article.getStock().getId();

            Stock stock = stockRepository.findById(stockId)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Stock introuvable avec l'id : "
                            + stockId));

            existant.setStock(stock);
        }

        return articleRepository.save(existant);
    }

    @Override
    public Article findById(Long id) {

        return articleRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Article introuvable avec l'id : " + id));
    }

    @Override
    public Article findByCode(String code) {

        return articleRepository.findByCode(code)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Article introuvable avec le code : "
                        + code));
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> findByStock(Long stockId) {

        return articleRepository.findByStockId(stockId);
    }

    @Override
    public List<Article> searchByNom(String nom) {

        return articleRepository
                .findByNomContainingIgnoreCase(nom);
    }

    @Override
    public void delete(Long id) {

        if (!articleRepository.existsById(id)) {
            throw new RuntimeException(
                    "Article introuvable avec l'id : " + id);
        }

        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> importFromMultiSheetExcel(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Le fichier Excel est vide.");
        }

        List<Article> articlesImportes = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            // Onglets à ignorer
            List<String> ongletsAIgnorer = Arrays.asList(
                "Réponses au formulaire 2", "sismique", "Historique_Mouvement"
            );

            // Parcours des feuilles Excel (Chaque feuille = Un Stock)
            for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
                Sheet sheet = workbook.getSheetAt(s);
                String nomStock = sheet.getSheetName().trim();

                if (ongletsAIgnorer.contains(nomStock)) {
                    continue;
                }

                // 1. Récupération ou création du Stock par le nom de l'onglet
                Stock stock = stockRepository.findByNom(nomStock)
                        .orElseGet(() -> {
                            Stock nouveauStock = new Stock();
                            nouveauStock.setNom(nomStock);
                            nouveauStock.setDescription("Stock de la catégorie " + nomStock);
                            return stockRepository.save(nouveauStock);
                        });

                // 2. Lecture des lignes
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;

                    String ref       = getCellValueAsString(row.getCell(0)); // Col 0: Ref
                    String marque    = getCellValueAsString(row.getCell(1)); // Col 1: Marque
                    String type      = getCellValueAsString(row.getCell(2)); // Col 2: Type
                    String reference = getCellValueAsString(row.getCell(3)); // Col 3: Référence
                    int quantite     = getCellValueAsInt(row.getCell(4));    // Col 4: Quantité (int)

                    // Ignorer les lignes sans référence d'article
                    if (ref == null || ref.trim().isEmpty()) {
                        continue;
                    }

                    Article article = new Article();
                    article.setCode(ref.trim());
                    
                    // --- NOM : Marque + Type ---
                    String nomCompose = String.join(" ", 
                        marque != null ? marque : "", 
                        type != null ? type : ""
                    ).trim();
                    article.setNom(nomCompose.isEmpty() ? "Article " + ref : nomCompose);

                    // --- DESCRIPTION : Marque + Type + Reference ---
                    String descCompose = String.join(" - ", 
                        nomCompose, 
                        reference != null ? reference : ""
                    ).trim();
                    article.setDescription(descCompose);

                    // --- QUANTITÉ INITIALE ---
                    article.setQuantite(quantite);
                    
                    article.setDateCreation(LocalDateTime.now());
                    article.setStock(stock);

                    // Sauvegarde avec tes validations existantes
                    Article articleSauvegarde = this.save(article);
                    articlesImportes.add(articleSauvegarde);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'importation du fichier Excel : " + e.getMessage(), e);
        }

        return articlesImportes;
    }

    // --- Utility Methods ---

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return null;
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }

    private int getCellValueAsInt(Cell cell) {
        if (cell == null) return 0;

        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) Math.round(cell.getNumericCellValue());
        }

        try {
            String val = cell.getStringCellValue().trim()
                    .replace(",", ".")
                    .replaceAll("[^0-9.]", "");

            return val.isEmpty() ? 0 : (int) Math.round(Double.parseDouble(val));
        } catch (Exception e) {
            return 0;
        }
    }}

