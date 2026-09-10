package com.esnet.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esnet.beans.Stock;
import com.esnet.repository.StockRepository;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	@Autowired
    private StockRepository stockRepository;

    

    @Override
    public Stock save(Stock stock) {

        if (stock.getNom() == null || stock.getNom().trim().isEmpty()) {
            throw new RuntimeException("Le nom du stock est obligatoire");
        }

        if (stockRepository.existsByNom(stock.getNom())) {
            throw new RuntimeException(
                    "Un stock existe déjà avec le nom : " + stock.getNom());
        }

        if (stock.getDateCreation() == null) {
            stock.setDateCreation(
                    java.time.LocalDateTime.now());
        }

        return stockRepository.save(stock);
    }

    @Override
    public Stock update(Long id, Stock stock) {

        Stock existant = findById(id);

        if (stock.getNom() == null || stock.getNom().trim().isEmpty()) {
            throw new RuntimeException("Le nom du stock est obligatoire");
        }

        existant.setNom(stock.getNom());
        existant.setDescription(stock.getDescription());

        return stockRepository.save(existant);
    }

    @Override
    public Stock findById(Long id) {

        return stockRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Stock introuvable avec l'id : " + id));
    }

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public Stock findByNom(String nom) {

        return stockRepository.findByNom(nom)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Stock introuvable avec le nom : " + nom));
    }

    @Override
    public List<Stock> searchByNom(String nom) {

        return stockRepository
                .findByNomContainingIgnoreCase(nom);
    }

    @Override
    public void delete(Long id) {

        if (!stockRepository.existsById(id)) {
            throw new RuntimeException(
                    "Stock introuvable avec l'id : " + id);
        }

        stockRepository.deleteById(id);
    }
}