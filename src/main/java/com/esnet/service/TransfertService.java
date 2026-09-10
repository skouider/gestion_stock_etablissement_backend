package com.esnet.service;

import java.time.LocalDateTime;
import java.util.List;

import com.esnet.beans.Transfert;

public interface TransfertService {

    Transfert save(Transfert transfert);

    Transfert findById(Long id);

    List<Transfert> findAll();

    List<Transfert> findByArticle(Long articleId);

    List<Transfert> findByDepartementSource(Long departementId);

    List<Transfert> findByDepartementDestination(Long departementId);

    List<Transfert> findByDateBetween(
            LocalDateTime dateDebut,
            LocalDateTime dateFin);

    void delete(Long id);
}