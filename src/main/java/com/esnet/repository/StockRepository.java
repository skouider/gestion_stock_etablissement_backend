package com.esnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esnet.beans.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{

    Optional<Stock> findByNom(String nom);

    boolean existsByNom(String nom);

    List<Stock> findByNomContainingIgnoreCase(String nom);

}
