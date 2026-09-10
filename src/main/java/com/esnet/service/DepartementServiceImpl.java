package com.esnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esnet.beans.Departement;
import com.esnet.repository.DepartementRepository;

@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {

    @Autowired
    private DepartementRepository departementRepository;


    // ==========================================
    // ENREGISTRER UN DEPARTEMENT
    // ==========================================

    @Override
    public Departement save(Departement departement) {

        // Vérification du nom
        if (departement.getNom() == null
                || departement.getNom().trim().isEmpty()) {

            throw new RuntimeException(
                    "Le nom du département est obligatoire");
        }

        // Vérification si le département existe déjà
        if (departementRepository
                .existsByNom(departement.getNom())) {

            throw new RuntimeException(
                    "Un département existe déjà avec le nom : "
                            + departement.getNom());
        }

        return departementRepository.save(departement);
    }


    // ==========================================
    // MODIFIER UN DEPARTEMENT
    // ==========================================

    @Override
    public Departement update(
            Long id,
            Departement departement) {

        // Recherche du département
        Departement existant = findById(id);

        // Vérification du nom
        if (departement.getNom() == null
                || departement.getNom().trim().isEmpty()) {

            throw new RuntimeException(
                    "Le nom du département est obligatoire");
        }

        existant.setNom(departement.getNom());

        return departementRepository.save(existant);
    }


    // ==========================================
    // RECHERCHER PAR ID
    // ==========================================

    @Override
    public Departement findById(Long id) {

        return departementRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Département introuvable avec l'id : "
                                        + id));
    }


    // ==========================================
    // RECHERCHER PAR NOM
    // ==========================================

    @Override
    public Departement findByNom(String nom) {

        return departementRepository
                .findByNom(nom)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Département introuvable avec le nom : "
                                        + nom));
    }


    // ==========================================
    // LISTE DES DEPARTEMENTS
    // ==========================================

    @Override
    public List<Departement> findAll() {

        return departementRepository.findAll();
    }


    // ==========================================
    // RECHERCHE PAR NOM
    // ==========================================

    @Override
    public List<Departement> searchByNom(String nom) {

        return departementRepository
                .findByNomContainingIgnoreCase(nom);
    }


    // ==========================================
    // SUPPRIMER UN DEPARTEMENT
    // ==========================================

    @Override
    public void delete(Long id) {

        if (!departementRepository.existsById(id)) {

            throw new RuntimeException(
                    "Département introuvable avec l'id : "
                            + id);
        }

        departementRepository.deleteById(id);
    }
}