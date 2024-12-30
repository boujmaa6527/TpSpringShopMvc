package fr.fms.dao;

import fr.fms.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

             //List<Article> findAll();
             Page<Article> findByDescriptionContains(Pageable pageable, String descrption);

             //@Query("select A from Article A  WHERE A.category  = :category_id")
             Page<Article> findByCategoryId(Long category_id, Pageable pageable);

//            @Modifying
//            @Transactional
//            @Query("UPDATE Article a SET a.marque = :marque, a.description = :description, a.price = :price WHERE a.id = :id")
//            void updateArticleById(@Param("id") Long id,
//                                          @Param("brand") String brand,
//                                          @Param("description") String description,
//                                          @Param("price") double price);


}
