package com.esnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esnet.beans.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByCode(String code);

    boolean existsByCode(String code);

    List<Article> findByNomContainingIgnoreCase(String nom);

    List<Article> findByStockId(Long stockId);

    List<Article> findByStockIdAndNomContainingIgnoreCase(
            Long stockId,
            String nom
    );
}