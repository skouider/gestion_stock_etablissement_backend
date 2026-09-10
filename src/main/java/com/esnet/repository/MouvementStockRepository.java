package com.esnet.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esnet.beans.MouvementStock;
import com.esnet.beans.TypeMouvementStock;

@Repository
public interface MouvementStockRepository
        extends JpaRepository<MouvementStock, Long> {

    // Historique d'un stock
    List<MouvementStock> findByStockId(
            Long stockId
    );

    // Historique d'un stock, plus récent en premier
    List<MouvementStock> findByStockIdOrderByDateMouvementDesc(
            Long stockId
    );

    // Historique d'un article
    List<MouvementStock> findByArticleId(
            Long articleId
    );

    // Historique d'un article, plus récent en premier
    List<MouvementStock> findByArticleIdOrderByDateMouvementDesc(
            Long articleId
    );

    // Entrées ou sorties d'un article
    List<MouvementStock> findByArticleIdAndTypeMouvement(
            Long articleId,
            TypeMouvementStock typeMouvement
    );

    // Entrées ou sorties d'un stock
    List<MouvementStock> findByStockIdAndTypeMouvement(
            Long stockId,
            TypeMouvementStock typeMouvement
    );

    // Tous les mouvements d'un type
    List<MouvementStock> findByTypeMouvement(
            TypeMouvementStock typeMouvement
    );

    // Mouvements effectués par un utilisateur
    List<MouvementStock> findByUtilisateurId(
            Long utilisateurId
    );

    // Mouvements destinés à un département
    List<MouvementStock> findByDepartementDestinationId(
            Long departementId
    );

    // Mouvements entre deux dates
    List<MouvementStock> findByDateMouvementBetween(
            LocalDateTime dateDebut,
            LocalDateTime dateFin
    );

    // Historique d'un article entre deux dates
    List<MouvementStock> findByArticleIdAndDateMouvementBetween(
            Long articleId,
            LocalDateTime dateDebut,
            LocalDateTime dateFin
    );

    // Historique d'un stock entre deux dates
    List<MouvementStock> findByStockIdAndDateMouvementBetween(
            Long stockId,
            LocalDateTime dateDebut,
            LocalDateTime dateFin
    );
}