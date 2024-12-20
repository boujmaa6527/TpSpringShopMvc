package fr.fms.dao;

import fr.fms.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

             //List<Article> findAll();
             Page<Article> findByDescriptionContains( Pageable pageable, String descrption);


 }
