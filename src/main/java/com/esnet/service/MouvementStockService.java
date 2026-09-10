package com.esnet.service;

import java.time.LocalDateTime;
import java.util.List;

import com.esnet.beans.MouvementStock;
import com.esnet.beans.TypeMouvementStock;

public interface MouvementStockService {

    MouvementStock save(MouvementStock mouvement);

    MouvementStock findById(Long id);

    List<MouvementStock> findAll();

    List<MouvementStock> findByStock(Long stockId);

    List<MouvementStock> findByArticle(Long articleId);

    List<MouvementStock> findByType(TypeMouvementStock type);

    List<MouvementStock> findByStockAndType(
            Long stockId,
            TypeMouvementStock type);

    List<MouvementStock> findByArticleAndType(
            Long articleId,
            TypeMouvementStock type);

    List<MouvementStock> findByDateBetween(
            LocalDateTime dateDebut,
            LocalDateTime dateFin);

    MouvementStock entree(
            Long articleId,
            int quantite,
            String description);

    MouvementStock sortie(
            Long articleId,
            int quantite,
            Long departementId,
            String description);

    void delete(Long id);
}