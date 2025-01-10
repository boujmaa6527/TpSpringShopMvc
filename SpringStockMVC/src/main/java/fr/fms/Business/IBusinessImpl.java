package fr.fms.Business;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class IBusinessImpl implements IBusiness{

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private HashMap<Long, Article> cart;

    @Getter
    @Setter
    private Customer customer;

    public IBusinessImpl(){
        cart = new HashMap<>();
        customer = null;
    }

    @Override
    public void addArtToCart(Article article){
        Article a =  cart.get(article.getId());
        if(a !=  null){
            a.setQuantity(a.getQuantity() +1);
        }else{
            cart.put(article.getId(), article);
        }
    }
    @Override
    public void delArtFromCart(Long id){
        cart.remove(id);
    }
    @Override
    public void delCart(){
        cart.clear();
    }

    @Override
    public List<Article> getCart(){
        return new ArrayList<>(cart.values());
    }

    public double getTotalAmount(){
        double total = 0;
        for(Article article : cart.values()){
            total += article.getPrice() * article.getQuantity();
        }
        return total;
    }

    public boolean isEmpty(){
        return cart.isEmpty();
    }

    @Override
    public int getNbCart(){
        return  cart.size();

    }
    @Override
    public  List<Article> getArticles() throws Exception{
        return articleRepository.findAll();
    }
    @Override
    public Article getArticleById(Long id) throws Exception{
        Optional<Article> optional = articleRepository.findById(id);
        return optional.isPresent()? optional.get() : null;
    }
    @Override
    public void saveArticle(Article article) throws Exception{
        articleRepository.save(article);
    }

    @Override
    public Page<Article> getArticlesByCategoriePage(Long category_id, int page) throws Exception{
        return articleRepository.findByCategoryId(category_id, PageRequest.of(page, 5));
    }

    @Override
    public Page<Article> getArticlesPages(String kw, int page) throws Exception{
        return articleRepository.findByDescriptionContains(PageRequest.of(page, 5), kw);
    }

    @Override
    public void deleteArticle(Long id) throws Exception{
        articleRepository.deleteById(id);
    }
    public List<Category> getCategories() throws Exception{
        return categoryRepository.findAll();
    }
    public Category getCategoryById(Long id) throws Exception{
        return categoryRepository.getById(id);
    }

    public void order(){
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    public String great(){
        return "Hello world";
    }





}
