package com.esnet.service;

import java.util.List;

import com.esnet.beans.Departement;

public interface DepartementService {

    Departement save(Departement departement);

    Departement update(Long id, Departement departement);

    Departement findById(Long id);

    Departement findByNom(String nom);

    List<Departement> findAll();

    List<Departement> searchByNom(String nom);

    void delete(Long id);
}