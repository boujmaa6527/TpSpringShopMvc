package fr.fms.web;

import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class CategoryConroler {

    @Autowired
    CategoryRepository categoryRepository;

    private final Logger logger = LoggerFactory.getLogger(CategoryConroler.class);

    @GetMapping("/category")
    public String categories(Long id, Model model){
        Long category_id =(long) -1;
        try {
            Optional<Category> categoryOptiona = categoryRepository.findById(category_id+1);
            if(categoryOptiona.isPresent()){
                Category category = categoryOptiona.get();
                category_id = category.getId();
                model.addAttribute("category_id", category_id);
            }else {
                return "redirect:/index";
            }
        }catch (Exception e){
            logger.error("[CATEGORY CONTROLLER : CATEGORY] : {} ", e.getMessage());
            return "redirect:/index?error=" + e.getMessage();
        }
        return "redirect:/index?category_id="+ category_id;
    }
}
