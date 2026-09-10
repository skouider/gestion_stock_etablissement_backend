package com.esnet.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esnet.beans.Transfert;

@Repository
public interface TransfertRepository
        extends JpaRepository<Transfert, Long> {

    // Historique des transferts d'un article
    List<Transfert> findByArticleId(
            Long articleId
    );

    // Plus récent en premier
    List<Transfert> findByArticleIdOrderByDateTransfertDesc(
            Long articleId
    );

    // Transferts provenant d'un département
    List<Transfert> findByDepartementSourceId(
            Long departementId
    );

    // Transferts vers un département
    List<Transfert> findByDepartementDestinationId(
            Long departementId
    );

    // Transfert d'un département vers un autre
    List<Transfert> findByDepartementSourceIdAndDepartementDestinationId(
            Long sourceId,
            Long destinationId
    );

    // Transferts effectués par un utilisateur
    List<Transfert> findByUtilisateurId(
            Long utilisateurId
    );

    // Transferts entre deux dates
    List<Transfert> findByDateTransfertBetween(
            LocalDateTime dateDebut,
            LocalDateTime dateFin
    );
}