package com.esnet.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esnet.beans.MouvementStock;
import com.esnet.beans.TypeMouvementStock;
import com.esnet.dto.MouvementStockDTO;
import com.esnet.service.MouvementStockService;

@RestController
@RequestMapping("/api/v1/mouvements-stock")
@CrossOrigin("*")
public class MouvementStockController {

    @Autowired
    private MouvementStockService mouvementStockService;


    // ==========================================
    // TOUS LES MOUVEMENTS
    // ==========================================

    @GetMapping
    public List<MouvementStock> findAll() {

        return mouvementStockService.findAll();
    }


    // ==========================================
    // TROUVER UN MOUVEMENT PAR ID
    // ==========================================

    @GetMapping("/{id}")
    public MouvementStock findById(
            @PathVariable Long id) {

        return mouvementStockService.findById(id);
    }


    // ==========================================
    // HISTORIQUE D'UN STOCK
    // ==========================================

    @GetMapping("/stock/{stockId}")
    public List<MouvementStock> findByStock(
            @PathVariable Long stockId) {

        return mouvementStockService
                .findByStock(stockId);
    }


    // ==========================================
    // HISTORIQUE D'UN ARTICLE
    // ==========================================

    @GetMapping("/article/{articleId}")
    public List<MouvementStock> findByArticle(
            @PathVariable Long articleId) {

        return mouvementStockService
                .findByArticle(articleId);
    }


    // ==========================================
    // RECHERCHE PAR TYPE
    // ENTREE / SORTIE
    // ==========================================

    @GetMapping("/type/{type}")
    public List<MouvementStock> findByType(
            @PathVariable TypeMouvementStock type) {

        return mouvementStockService
                .findByType(type);
    }


    // ==========================================
    // STOCK + TYPE
    // ==========================================

    @GetMapping("/stock/{stockId}/type/{type}")
    public List<MouvementStock> findByStockAndType(
            @PathVariable Long stockId,

            @PathVariable TypeMouvementStock type) {

        return mouvementStockService
                .findByStockAndType(stockId, type);
    }


    // ==========================================
    // ARTICLE + TYPE
    // ==========================================

    @GetMapping("/article/{articleId}/type/{type}")
    public List<MouvementStock> findByArticleAndType(
            @PathVariable Long articleId,

            @PathVariable TypeMouvementStock type) {

        return mouvementStockService
                .findByArticleAndType(articleId, type);
    }


    // ==========================================
    // RECHERCHE PAR PERIODE
    // ==========================================

    @GetMapping("/periode")
    public List<MouvementStock> findByDateBetween(@RequestParam  @DateTimeFormat(
                iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateDebut,
            @RequestParam @DateTimeFormat(
                iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFin) {

        return mouvementStockService.findByDateBetween(dateDebut,dateFin);
    }


    // ==========================================
    // ENTREE DE STOCK
    // ==========================================

//    @PostMapping("/entree")
//    public MouvementStock entree(@RequestParam Long articleId,@RequestParam int quantite,
//            @RequestParam(required = false)String description) {
//
//        return mouvementStockService.entree(articleId,quantite,description);
//    }


    // ==========================================
    // SORTIE DE STOCK
    // ==========================================

//    @PostMapping("/sortie")
//    public MouvementStock sortie(@RequestParam Long articleId,@RequestParam int quantite,
//            @RequestParam(required = false)Long departementId,@RequestParam(required = false)
//            String description) {
//
//        return mouvementStockService.sortie(
//                articleId,
//                quantite,
//                departementId,
//                description);
//    }


    // ==========================================
    // SUPPRIMER UN MOUVEMENT
    // ==========================================

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        mouvementStockService.delete(id);
    }
    
    
//    @PostMapping("/entree")
//    public ResponseEntity<?> enregistrerEntree(@RequestBody MouvementStockDTO dto) {
//        try {
//            MouvementStock mouvement = mouvementStockService.entree(
//                dto.getArticleId(),
//                dto.getQuantite(),
//                dto.getDescription()
//            );
//            return ResponseEntity.ok(mouvement);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
//
//    @PostMapping("/sortie")
//    public ResponseEntity<?> enregistrerSortie(@RequestBody MouvementStockDTO dto) {
//        try {
//            MouvementStock mouvement = mouvementStockService.sortie(
//                dto.getArticleId(),
//                dto.getQuantite(),
//                dto.getStockId(),
//                dto.getDescription()
//            );
//            return ResponseEntity.ok(mouvement);
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
    
    @PostMapping("/entree")
    public ResponseEntity<?> enregistrerEntree(@RequestBody MouvementStockDTO dto) {
        try {
            MouvementStock mouvement = mouvementStockService.entree(
                dto.getArticleId(),
                dto.getQuantite(),
                dto.getDescription()
            );

            // Transformation en DTO simple sans boucles d'objets
            MouvementStockDTO response = new MouvementStockDTO();
            response.setArticleId(mouvement.getArticle().getId());
            response.setQuantite(mouvement.getQuantite());
            response.setTypeMouvement(mouvement.getTypeMouvement());
            response.setDescription(mouvement.getDescription());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/sortie")
    public ResponseEntity<?> enregistrerSortie(@RequestBody MouvementStockDTO dto) {
        try {
            MouvementStock mouvement = mouvementStockService.sortie(
                dto.getArticleId(),
                dto.getQuantite(),
                dto.getStockId(),
                dto.getDescription()
            );

            MouvementStockDTO response = new MouvementStockDTO();
            response.setArticleId(mouvement.getArticle().getId());
            response.setQuantite(mouvement.getQuantite());
            response.setTypeMouvement(mouvement.getTypeMouvement());
            response.setStockId(dto.getStockId());
            response.setDescription(mouvement.getDescription());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
