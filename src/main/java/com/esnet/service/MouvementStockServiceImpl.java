package com.esnet.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esnet.beans.Article;
import com.esnet.beans.Departement;
import com.esnet.beans.MouvementStock;
import com.esnet.beans.Stock;
import com.esnet.beans.TypeMouvementStock;
import com.esnet.repository.ArticleRepository;
import com.esnet.repository.DepartementRepository;
import com.esnet.repository.MouvementStockRepository;

@Service
@Transactional
public class MouvementStockServiceImpl
        implements MouvementStockService {

	@Autowired
    private MouvementStockRepository mouvementStockRepository;
    @Autowired
	private  ArticleRepository articleRepository;
    @Autowired
    private  DepartementRepository departementRepository;

    
    @Override
    public MouvementStock save(MouvementStock mouvement) {

        return mouvementStockRepository.save(mouvement);
    }

    @Override
    public MouvementStock findById(Long id) {

        return mouvementStockRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Mouvement introuvable avec l'id : "
                        + id));
    }

    @Override
    public List<MouvementStock> findAll() {

        return mouvementStockRepository.findAll();
    }

    @Override
    public List<MouvementStock> findByStock(Long stockId) {

        return mouvementStockRepository
                .findByStockId(stockId);
    }

    @Override
    public List<MouvementStock> findByArticle(Long articleId) {

        return mouvementStockRepository
                .findByArticleId(articleId);
    }

    @Override
    public List<MouvementStock> findByType(
            TypeMouvementStock type) {

        return mouvementStockRepository
                .findByTypeMouvement(type);
    }

    @Override
    public List<MouvementStock> findByStockAndType(
            Long stockId,
            TypeMouvementStock type) {

        return mouvementStockRepository
                .findByStockIdAndTypeMouvement(
                        stockId, type);
    }

    @Override
    public List<MouvementStock> findByArticleAndType(
            Long articleId,
            TypeMouvementStock type) {

        return mouvementStockRepository
                .findByArticleIdAndTypeMouvement(
                        articleId, type);
    }

    @Override
    public List<MouvementStock> findByDateBetween(
            LocalDateTime dateDebut,
            LocalDateTime dateFin) {

        return mouvementStockRepository
                .findByDateMouvementBetween(
                        dateDebut, dateFin);
    }

    /*
     * =========================================================
     * ENTREE DE STOCK
     * =========================================================
     */

    @Override
    public MouvementStock entree(
            Long articleId,
            int quantite,
            String description) {

        // Vérification de la quantité
        if (quantite <= 0) {
            throw new RuntimeException(
                    "La quantité doit être supérieure à zéro");
        }

        // Recherche de l'article
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Article introuvable avec l'id : "
                        + articleId));

        // Vérification du stock
        Stock stock = article.getStock();

        if (stock == null) {
            throw new RuntimeException(
                    "L'article n'est associé à aucun stock");
        }

        /*
         * Modification de la quantité
         *
         * Exemple :
         * 50 + 10 = 60
         */
        int nouvelleQuantite =
                article.getQuantite() + quantite;

        article.setQuantite(nouvelleQuantite);

        articleRepository.save(article);

        /*
         * Création de l'historique du mouvement
         */
        MouvementStock mouvement = new MouvementStock();

        mouvement.setDateMouvement(LocalDateTime.now());
        mouvement.setQuantite(quantite);
        mouvement.setTypeMouvement(
                TypeMouvementStock.ENTREE);
        mouvement.setDescription(description);

        mouvement.setArticle(article);
        mouvement.setStock(stock);

        return mouvementStockRepository.save(mouvement);
    }

    /*
     * =========================================================
     * SORTIE DE STOCK
     * =========================================================
     */

    @Override
    public MouvementStock sortie(
            Long articleId,
            int quantite,
            Long departementId,
            String description) {

        // Vérification de la quantité
        if (quantite <= 0) {
            throw new RuntimeException(
                    "La quantité doit être supérieure à zéro");
        }

        // Recherche de l'article
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Article introuvable avec l'id : "
                        + articleId));

        /*
         * Vérification du stock
         */
        Stock stock = article.getStock();

        if (stock == null) {
            throw new RuntimeException(
                    "L'article n'est associé à aucun stock");
        }

        /*
         * Vérification de la quantité disponible
         *
         * Exemple :
         *
         * stock = 50
         * sortie = 1
         *
         * résultat = 49
         */
        if (article.getQuantite() < quantite) {

            throw new RuntimeException(
                    "Stock insuffisant. Quantité disponible : "
                    + article.getQuantite());
        }

        /*
         * Modification de la quantité
         */
        int nouvelleQuantite =
                article.getQuantite() - quantite;

        article.setQuantite(nouvelleQuantite);

        articleRepository.save(article);

        /*
         * Création du mouvement
         */
        MouvementStock mouvement = new MouvementStock();

        mouvement.setDateMouvement(LocalDateTime.now());
        mouvement.setQuantite(quantite);
        mouvement.setTypeMouvement(
                TypeMouvementStock.SORTIE);
        mouvement.setDescription(description);

        mouvement.setArticle(article);
        mouvement.setStock(stock);

        /*
         * Département destination
         */
        if (departementId != null) {

            Departement departement =
                    departementRepository.findById(departementId)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Département introuvable avec l'id : "
                            + departementId));

            mouvement.setDepartementDestination(
                    departement);
        }

        return mouvementStockRepository.save(mouvement);
    }

    @Override
    public void delete(Long id) {

        if (!mouvementStockRepository.existsById(id)) {

            throw new RuntimeException(
                    "Mouvement introuvable avec l'id : "
                    + id);
        }

        mouvementStockRepository.deleteById(id);
    }

	

	
}