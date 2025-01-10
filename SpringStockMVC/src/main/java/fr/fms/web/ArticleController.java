package fr.fms.web;

import fr.fms.Business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
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
    IBusinessImpl businessImpl; 
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;

        @GetMapping("/index")
        public String index(Model model, @RequestParam(name="page", defaultValue = "0" )int page,
                                         @RequestParam(name= "keyword", defaultValue = "") String kw,
                                         @RequestParam(name = "nbCart", defaultValue = "0") int cart,
                                         @RequestParam(name = "category_id", defaultValue = "0") Long category_id,
                                         @ModelAttribute(name = "error") String error) throws Exception {
             //Page<Article> articles = articleRepository.findAll(PageRequest.of(page, 5));
            Page<Article> articles = null;
            Page<Article> articleByCategorie = null;
            
            //List<Category> categories = categoryRepository.findAll();

                if(category_id > 0) {

                    Optional<Category> categoryToDisplay = categoryRepository.findById(category_id);
                    articles = businessImpl.getArticlesByCategoriePage(category_id, page);
                    if(articles.isEmpty()){
                        //model.addAttribute("error", ManageErrors.STR_ERROR);
                    }else{
                        articles = businessImpl.getArticlesPages(kw, page);
                    }
                  //  if(categoryToDisplay.isPresent()){
                        articles = businessImpl.getArticlesByCategoriePage(category_id,page);
                        Category category = categoryToDisplay.get();
                        System.out.println("category id :" + category.getId());

                    articleByCategorie = businessImpl.getArticlesByCategoriePage(category.getId(), page);
                        System.out.println("Article found: " + articleByCategorie.getTotalElements());
////                    }else{
////                        articles = Page.empty();
////                        System.out.println("Category not found" + category_id);
//                    }
                }else{
                    articles = articleRepository.findByDescriptionContains(PageRequest.of(page, 5), kw);
                    System.out.println("id not null");
            }

            List<Category> categories = businessImpl.getCategories();

            model.addAttribute("listArticle", articles.getContent());

            model.addAttribute("pages", new int[articles.getTotalPages()]);

            model.addAttribute("currentPage",page);

            model.addAttribute("keyword", kw);

            model.addAttribute("categories", businessImpl.getCategories());

            model.addAttribute("category_id", category_id);
            model.addAttribute("nbcart", businessImpl.getNbCart());

            if(category_id > 0 && articleByCategorie != null) {
                model.addAttribute("articleByCategorie", articleByCategorie.getContent());
                System.out.println(articleByCategorie.getContent());
            }
            return "articles";
        }

        @GetMapping("/delete")
        public String delete(Long id, int page, String keyword){
            Optional<Article> articleDell = articleRepository.findById(id);
            if(articleDell.isPresent()){
                System.out.println("Article dell "+ articleDell);
                articleRepository.deleteById(articleDell.get().getId());
            }

            return "redirect:/index?category_id=1";
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
            System.out.println("article" +article);

            if(bindingResult.hasErrors()) {
                System.out.println("Validation error" + bindingResult.getAllErrors());
                //return "update";
                return "article";
            }
            articleRepository.save(article);
            System.out.println(article);
            return "redirect:/index";
        }
        @GetMapping("/update/{id}")
        public String update(@PathVariable(value = "id") Long id , Model model){
            System.out.println("update");
            Optional<Article> articleUpdate = articleRepository.findById(id);
            if(articleUpdate.isPresent()){
                Article article = articleUpdate.get();
                //Optional<Category> categorie =  categoryRepository.findById(id);
                model.addAttribute("article", article);
                model.addAttribute("categories",categoryRepository.findAll());
                //System.out.println("Article update" + article);
                //articleRepository.save(article);
                return "update";
            }else {
                System.out.println(("Not found Article " +id));
                return "Error";
            }

        }
//        @PostMapping("/index")
//        public String viewHomePage(@RequestParam(name = "page", defaultValue = "0") int page,
//                                   @RequestParam(name= "keyword", defaultValue = "") String kw,
//                                   @RequestParam(name = "category_id") Long categoryId,
//                                   Model model){
//            Page<Article> articles = articleRepository.findByCategoryId(categoryId,kw, PageRequest.of(page, 5));
//            model.addAttribute("pages", new int[articles.getTotalPages()]);
//
//            model.addAttribute("currentPage",page);
//
//            model.addAttribute("keyword", kw);
//            model.addAttribute("articles", articles.getContent());
//            return  "articles";
//
//         }

        @GetMapping("/")
        public String home(){
            return "redirect:/index";
        }
}

