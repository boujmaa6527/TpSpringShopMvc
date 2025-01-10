package fr.fms.dao;

import fr.fms.entities.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

            List<Article> findByBrand(String brand);
            List<Article> findByBrandContains(String brand);
            List<Article> findByBrandAndPrice(String brand, double price);
            List<Article> findByBrandContainsAndPriceGreaterThan(String brand, double price);

            @Query("select A from Article A where A.brand like %:x% and A.price > :y")
            List<Article> searchArticles(@Param("x") String brand, @Param("y") double price);

            @Query("select A from Article A where A.id= :id")
            Article FindOne(@Param("id") long id);

            List<Article> findByDescriptionAndBrand(String description, String brand);

            Page<Article> findByDescriptionContains(Pageable pageable, String descrption);

             Page<Article> findByCategoryId(Long category_id, String kw, Pageable pageable);


            @Override
            Page<Article> findAll(Pageable pageable);

            List<Article> findByCategoryId(Long category_id);

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
