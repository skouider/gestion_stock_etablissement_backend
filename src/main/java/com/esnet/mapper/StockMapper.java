package com.esnet.mapper;

import com.esnet.beans.Stock;
import com.esnet.dto.StockDTO;
import com.esnet.dto.StockRequestDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StockMapper {

    public StockDTO toDto(Stock stock) {
        if (stock == null) return null;

        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setNom(stock.getNom());
        dto.setDescription(stock.getDescription());
        dto.setDateCreation(stock.getDateCreation());
        
        // Calcul du nombre d'articles associés au stock
        if (stock.getArticles() != null) {
            dto.setNombreArticles(stock.getArticles().size());
        } else {
            dto.setNombreArticles(0);
        }

        return dto;
    }

    public Stock toEntity(StockRequestDTO dto) {
        if (dto == null) return null;

        Stock stock = new Stock();
        stock.setNom(dto.getNom());
        stock.setDescription(dto.getDescription());
        stock.setDateCreation(LocalDateTime.now());

        return stock;
    }
}