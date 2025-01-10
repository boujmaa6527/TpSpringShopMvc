package fr.fms.Business;

import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBusiness {

    public List<Article> getArticles() throws Exception;


    public Page<Article> getArticlesPages(String kw, int page) throws Exception;

    public Page<Article> getArticlesByCategoriePage(Long category_id, int age) throws Exception;

    public void saveArticle(Article article) throws Exception;

    public Article getArticleById(Long id) throws Exception;

    public void deleteArticle(Long id) throws  Exception;

    public List<Category> getCategories() throws  Exception;

    public void addArtToCart(Article article);

    public void delArtFromCart(Long id);

    public void delCart();

    public List<Article> getCart();

    public int getNbCart();

}
