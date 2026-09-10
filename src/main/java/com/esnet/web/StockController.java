package com.esnet.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esnet.beans.Stock;
import com.esnet.service.StockService;

@RestController
@RequestMapping("/api/v1/stocks")
@CrossOrigin(origins = "*")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // =========================
    // CREATE
    // =========================

    @PostMapping
    public ResponseEntity<Stock> save(
            @RequestBody Stock stock) {

        Stock nouveauStock =
                stockService.save(stock);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nouveauStock);
    }

    // =========================
    // UPDATE
    // =========================

    @PutMapping("/{id}")
    public ResponseEntity<Stock> update(
            @PathVariable Long id,
            @RequestBody Stock stock) {

        Stock stockModifie =
                stockService.update(id, stock);

        return ResponseEntity.ok(stockModifie);
    }

    // =========================
    // FIND BY ID
    // =========================

    @GetMapping("/{id}")
    public ResponseEntity<Stock> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                stockService.findById(id)
        );
    }

    // =========================
    // FIND ALL
    // =========================

    @GetMapping
    public ResponseEntity<List<Stock>> findAll() {

        return ResponseEntity.ok(
                stockService.findAll()
        );
    }

    // =========================
    // FIND BY NAME
    // =========================

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Stock> findByNom(
            @PathVariable String nom) {

        return ResponseEntity.ok(
                stockService.findByNom(nom)
        );
    }

    // =========================
    // SEARCH
    // =========================

    @GetMapping("/search")
    public ResponseEntity<List<Stock>> searchByNom(
            @RequestParam String nom) {

        return ResponseEntity.ok(
                stockService.searchByNom(nom)
        );
    }

    // =========================
    // DELETE
    // =========================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        stockService.delete(id);

        return ResponseEntity.noContent().build();
    }
}