package fr.fms.web;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;



        @GetMapping("/index")
        public String index(Model model, @RequestParam(name="page", defaultValue = "0" )int page,
                                         @RequestParam(name= "keyword", defaultValue = "") String kw,
                                         @RequestParam(required = false) Long category_id){
             //Page<Article> articles = articleRepository.findAll(PageRequest.of(page, 5));
            Page<Article> articles = articleRepository.findByDescriptionContains( PageRequest.of(page, 5), kw);
            Page<Article> articleByCategorie = null;
            
            List<Category> categories = categoryRepository.findAll();

                if(category_id != null) {


                    Optional<Category> categoryToDisplay = categoryRepository.findById(category_id);
                    if(categoryToDisplay.isPresent()){
                        Category category = categoryToDisplay.get();
                        System.out.println("category id :" + category.getId());

                        articleByCategorie = articleRepository.findByCategoryId(category.getId(),PageRequest.of(page, 5));
                        System.out.println("Article found: " + articleByCategorie.getTotalElements());
                    }else{
                        System.out.println("Category not found" + category_id);
                    }
                }else{
                    System.out.println("id not null");
            }





            //List<Article> articles = articleRepository.findAll();

            model.addAttribute("listArticle", articles.getContent());

            model.addAttribute("pages", new int[articles.getTotalPages()]);

            model.addAttribute("currentPage",page);

            model.addAttribute("keyword", kw);

            model.addAttribute("categories", categories);

            model.addAttribute("articleByCategorie", articleByCategorie.getContent());
            System.out.println(articleByCategorie.getContent());



            return "articles";

        }

        @GetMapping("/delete")
        public String delete(Long id, int page, String keyword){
            articleRepository.deleteById(id);
            return "redirect:/index?page="+page+"&keyword="+keyword;
        }
        @GetMapping("/article")
        public String article(Model model){

                model.addAttribute("article", new Article());
                model.addAttribute("categories", categoryRepository.findAll());
                System.out.println(categoryRepository.findAll());


            return "article";
        }
        @PostMapping("/save")
        public String save(@Valid Article article, BindingResult bindingResult){


            if(bindingResult.hasErrors()) return "article";
            articleRepository.save(article);
            return "redirect:/index";
        }
        @GetMapping("/update/{id}")
        public String update(@PathVariable(value = "id") Long id , Model model){

            Optional<Article> articleUpdate = articleRepository.findById(id);
            if(articleUpdate.isPresent()){
                Article article = articleUpdate.get();
                Optional<Category> categorie =  categoryRepository.findById(id);
                model.addAttribute("article", article);
                System.out.println("Article update" + article);
                article.setMarque(article.getMarque());
                article.setDescription(article.getDescription());
                article.setPrice(article.getPrice());
                article.setCategory(article.getCategory());
                articleRepository.save(article);
                return "update";
            }else {
                System.out.println(("Not found Article " +id));
                return "Error";
            }



        }
        @PostMapping("/index")
        public String viewHomePage(@RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name= "keyword", defaultValue = "") String kw,
                                   Model model){
            Page<Article> articles = articleRepository.findAll(PageRequest.of(page, 5));
            model.addAttribute("pages", new int[articles.getTotalPages()]);

            model.addAttribute("currentPage",page);

            model.addAttribute("keyword", kw);
            model.addAttribute("articles", articles.getContent());
            return  "articles";

        }
        @GetMapping("/")
        public String home(){
            return "redirect:/index?category_id=1";
        }



}

