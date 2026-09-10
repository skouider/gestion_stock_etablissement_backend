package com.esnet.dto;

import java.time.LocalDateTime;

public class StockDTO {
    private Long id;
    private String nom;
    private String description;
    private LocalDateTime dateCreation;
    private int nombreArticles; // Champ calculé pratique pour le Front-End

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public int getNombreArticles() { return nombreArticles; }
    public void setNombreArticles(int nombreArticles) { this.nombreArticles = nombreArticles; }
}