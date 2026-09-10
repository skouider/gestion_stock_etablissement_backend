package com.esnet.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.esnet.beans.Article;

public interface ArticleService {

    Article save(Article article);

    Article update(Long id, Article article);

    Article findById(Long id);

    Article findByCode(String code);

    List<Article> findAll();

    List<Article> findByStock(Long stockId);

    List<Article> searchByNom(String nom);
    
    public List<Article> importFromMultiSheetExcel(MultipartFile file);

    void delete(Long id);
}