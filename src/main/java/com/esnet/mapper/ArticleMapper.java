package com.esnet.mapper;

import com.esnet.beans.Article;
import com.esnet.dto.ArticleDTO;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleDTO toDto(Article article) {
        if (article == null) return null;
        ArticleDTO dto = new ArticleDTO();
        dto.setId(article.getId());
        dto.setCode(article.getCode());
        dto.setNom(article.getNom());
        dto.setDescription(article.getDescription());
        dto.setQuantite(article.getQuantite());
        dto.setDateCreation(article.getDateCreation());
        if (article.getStock() != null) {
            dto.setStockId(article.getStock().getId());
            dto.setStockNom(article.getStock().getNom());
        }
        return dto;
    }

    public Article toEntity(ArticleDTO dto) {
        if (dto == null) return null;
        Article article = new Article();
        article.setId(dto.getId());
        article.setCode(dto.getCode());
        article.setNom(dto.getNom());
        article.setDescription(dto.getDescription());
        article.setQuantite(dto.getQuantite());
        article.setDateCreation(dto.getDateCreation());
        return article;
    }
}