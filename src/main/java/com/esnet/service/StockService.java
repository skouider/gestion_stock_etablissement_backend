package com.esnet.service;

import java.util.List;

import com.esnet.beans.Stock;

public interface StockService {

    Stock save(Stock stock);

    Stock update(Long id, Stock stock);

    Stock findById(Long id);

    List<Stock> findAll();

    Stock findByNom(String nom);

    List<Stock> searchByNom(String nom);

    void delete(Long id);
}