package com.esnet.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esnet.beans.Article;
import com.esnet.dto.ArticleDTO;
import com.esnet.mapper.ArticleMapper;
import com.esnet.service.ArticleService;

@RestController
@RequestMapping("/api/v1/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleMapper articleMapper;

	// =========================
	// CREATE
	// =========================
	@PostMapping
	public ResponseEntity<Article> save(@RequestBody Article article) {
		System.out.println("code articles===" + article.getCode());
		Article nouvelArticle = articleService.save(article);
		System.out.println("code article 2===" + article.getCode());
		return ResponseEntity.status(HttpStatus.CREATED).body(nouvelArticle);
	}

	// =========================
	// UPDATE
	// =========================
	@PutMapping("/{id}")
	public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article article) {
		Article articleModifie = articleService.update(id, article);
		return ResponseEntity.ok(articleModifie);
	}

	// =========================
	// FIND BY ID
	// =========================
	@GetMapping("/{id}")
	public ResponseEntity<Article> findById(@PathVariable Long id) {
		return ResponseEntity.ok(articleService.findById(id));
	}

	// =========================
	// FIND ALL
	// =========================
	@GetMapping
	public ResponseEntity<List<Article>> findAll() {
		return ResponseEntity.ok(articleService.findAll());
	}

	// =========================
	// FIND BY NAME
	// =========================
	@GetMapping("/code/{code}")
	public ResponseEntity<Article> findByNom(@PathVariable String code) {
		return ResponseEntity.ok(articleService.findByCode(code));
	}

	// =========================
	// SEARCH
	// =========================
	@GetMapping("/search")
	public ResponseEntity<List<Article>> searchByNom(@RequestParam String nom) {
		return ResponseEntity.ok(articleService.searchByNom(nom));
	}

	@GetMapping("/stockId")
	public List<Article> findByStock(@RequestParam Long stockId) {

		return articleService.findByStock(stockId);
	}

	@GetMapping("/scan/{code}")
	public ResponseEntity<ArticleDTO> scanQrCode(@PathVariable String code) {
		Article article = articleService.findByCode(code);
		if (article == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(articleMapper.toDto(article));
	}

	// =========================
	// DELETE
	// =========================
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		articleService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
