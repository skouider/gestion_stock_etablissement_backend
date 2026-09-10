package com.esnet.dto;

import java.time.LocalDateTime;

public class ArticleDTO {
    private Long id;
    private String code;
    private String nom;
    private String description;
    private int quantite;
    private LocalDateTime dateCreation;
    private Long stockId;
    private String stockNom;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public Long getStockId() { return stockId; }
    public void setStockId(Long stockId) { this.stockId = stockId; }
    public String getStockNom() { return stockNom; }
    public void setStockNom(String stockNom) { this.stockNom = stockNom; }
}