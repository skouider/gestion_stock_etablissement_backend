package com.esnet.beans;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateTransfert;

    private Double quantite;

    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "departement_source_id")
    private Departement departementSource;

    @ManyToOne
    @JoinColumn(name = "departement_destination_id")
    private Departement departementDestination;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    public Transfert() {
    }

    public Transfert(Long id,
                     LocalDateTime dateTransfert,
                     Double quantite,
                     String commentaire) {
        this.id = id;
        this.dateTransfert = dateTransfert;
        this.quantite = quantite;
        this.commentaire = commentaire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTransfert() {
        return dateTransfert;
    }

    public void setDateTransfert(LocalDateTime dateTransfert) {
        this.dateTransfert = dateTransfert;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Departement getDepartementSource() {
        return departementSource;
    }

    public void setDepartementSource(Departement departementSource) {
        this.departementSource = departementSource;
    }

    public Departement getDepartementDestination() {
        return departementDestination;
    }

    public void setDepartementDestination(Departement departementDestination) {
        this.departementDestination = departementDestination;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}