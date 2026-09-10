package com.esnet.dto;

import com.esnet.beans.TypeMouvementStock;
import java.time.LocalDateTime;

public class MouvementStockDTO {
    private Long id;
    private LocalDateTime dateMouvement;
    private int quantite;
    private TypeMouvementStock typeMouvement;
    private String description;
    private Long articleId;
    private String articleCode;
    private String articleNom;
    private Long stockId;
    private Long utilisateurId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDateMouvement() { return dateMouvement; }
    public void setDateMouvement(LocalDateTime dateMouvement) { this.dateMouvement = dateMouvement; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public TypeMouvementStock getTypeMouvement() { return typeMouvement; }
    public void setTypeMouvement(TypeMouvementStock typeMouvement) { this.typeMouvement = typeMouvement; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getArticleId() { return articleId; }
    public void setArticleId(Long articleId) { this.articleId = articleId; }
    public String getArticleCode() { return articleCode; }
    public void setArticleCode(String articleCode) { this.articleCode = articleCode; }
    public String getArticleNom() { return articleNom; }
    public void setArticleNom(String articleNom) { this.articleNom = articleNom; }
    public Long getStockId() { return stockId; }
    public void setStockId(Long stockId) { this.stockId = stockId; }
    public Long getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(Long utilisateurId) { this.utilisateurId = utilisateurId; }
}