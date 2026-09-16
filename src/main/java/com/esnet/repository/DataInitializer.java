package com.esnet.repository;

import com.esnet.beans.Article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Optional;

@Component // <-- Laissez cette annotation active pour que Render exécute le script
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== DÉBUT DE L'IMPORTATION AUTOMATIQUE VIA RENDER ===");

        // ID de l'article présent sur TiDB Cloud (id = 1)
        Long idArticleExistant = 1L;

        // Charger l'image depuis le dossier static de l'application
        ClassPathResource imgResource = new ClassPathResource("static/photo_1.jpg");
        
        // Vérification de sécurité
        if (!imgResource.exists()) {
            System.out.println("❌ Erreur : L'image photo_1.jpg est introuvable dans src/main/resources/static/");
            return;
        }

        // Recherche de l'article dans TiDB Cloud
        Optional<Article> articleOptional = articleRepository.findById(idArticleExistant);

        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();

            // Lecture sécurisée du flux d'entrée (InputStream) de l'image
            try (InputStream is = imgResource.getInputStream()) {
                byte[] octetsImage = is.readAllBytes();
                article.setImageArticle(octetsImage);
                
                // Sauvegarde (UPDATE) dans TiDB Cloud
                articleRepository.save(article);
            }

            System.out.println("🎉 Magnifique ! Render a lu l'image interne et l'a injectée dans TiDB Cloud !");
        } else {
            System.out.println("❌ Erreur : Aucun article trouvé dans TiDB Cloud avec l'ID : " + idArticleExistant);
        }
    }
}
