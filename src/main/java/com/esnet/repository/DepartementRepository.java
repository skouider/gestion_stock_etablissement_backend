package com.esnet.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esnet.beans.Departement;

@Repository
public interface DepartementRepository
        extends JpaRepository<Departement, Long> {

    Optional<Departement> findByNom(String nom);

    boolean existsByNom(String nom);

    List<Departement> findByNomContainingIgnoreCase(String nom);
}