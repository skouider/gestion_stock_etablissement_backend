package com.esnet.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esnet.beans.Departement;
import com.esnet.service.DepartementService;

@RestController
@RequestMapping("/api/departements")
public class DepartementController {

    @Autowired
    private DepartementService departementService;


    // ==========================================
    // CREER UN DEPARTEMENT
    // ==========================================

    @PostMapping
    public Departement save(
            @RequestBody Departement departement) {

        return departementService.save(departement);
    }


    // ==========================================
    // MODIFIER UN DEPARTEMENT
    // ==========================================

    @PutMapping("/{id}")
    public Departement update(
            @PathVariable Long id,
            @RequestBody Departement departement) {

        return departementService.update(id, departement);
    }


    // ==========================================
    // TROUVER PAR ID
    // ==========================================

    @GetMapping("/{id}")
    public Departement findById(
            @PathVariable Long id) {

        return departementService.findById(id);
    }


    // ==========================================
    // TROUVER PAR NOM
    // ==========================================

    @GetMapping("/nom/{nom}")
    public Departement findByNom(
            @PathVariable String nom) {

        return departementService.findByNom(nom);
    }


    // ==========================================
    // LISTE DE TOUS LES DEPARTEMENTS
    // ==========================================

    @GetMapping
    public List<Departement> findAll() {

        return departementService.findAll();
    }


    // ==========================================
    // RECHERCHER PAR NOM
    // ==========================================

    @GetMapping("/search/{nom}")
    public List<Departement> searchByNom(
            @PathVariable String nom) {

        return departementService.searchByNom(nom);
    }


    // ==========================================
    // SUPPRIMER UN DEPARTEMENT
    // ==========================================

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        departementService.delete(id);
    }
}