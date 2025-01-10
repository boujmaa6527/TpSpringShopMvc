package fr.fms.web;

import fr.fms.Business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CustomerRepository;
import fr.fms.dao.OrderItemRepository;
import fr.fms.dao.OrderRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Customer;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;

@Controller
public class CartController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    IBusinessImpl business;

    private final Logger logger = LoggerFactory.getLogger(CartController.class);

    @GetMapping("/vcart")
    public String viewCaddy(Model model){
        model.addAttribute("cart", business.getCart());

        double total = business.getTotalAmount();
        model.addAttribute("total", total);
        model.addAttribute("nbArt", business.getNbCart());

        return "cart";
    }

    @GetMapping("/acart")
    public String addArticleCart(@RequestParam Long id,@RequestParam int page,@RequestParam  long category_id , RedirectAttributes redirectAttributes){
        System.out.println("acart");
        try{
            if(articleRepository.existsById(id)){
                business.addArtToCart(business.getArticleById(id));
                System.out.println("article  Id: " +id);

            }else{
                redirectAttributes.addFlashAttribute("error", "article not found");
            }
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            logger.error("[CART CONTROLER : ADD ARTICLE TO CART] : {}", e.getMessage());
            logger.info("id: "+ id + ",page:" +page+ ", catId:" + category_id);
        }
        System.out.println("busi"+ business.getNbCart());
        return "redirect:/index?page=" + page +  "&category_id=" + category_id + "&cart=" + business.getNbCart();
    }


    @GetMapping("/dcart")
    public String deleteArticleCart(Long id){
        business.delArtFromCart(id);
        return "redirect:/vcart";
    }

    @GetMapping("/order")
    public String order(Model model){
        if(business.isEmpty()) return "redirect:/index";
        model.addAttribute("customer", new Customer());
        return  "order";
    }

    @PostMapping("porder")
    public String postOrder(@Valid Customer customer, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) return "order";

        model.addAttribute("cart", business.getCart());
        double total = business.getTotalAmount();
        model.addAttribute("total", total);
        model.addAttribute("customer", customer);
        business.setCustomer(customer);
        return "information";
    }
    @GetMapping("/confirm")
    @Transactional
    public String confirm (RedirectAttributes redirectAttributes){
        if(business.isEmpty()) return "redirect:/index";
        try {
            Customer customer = business.getCustomer();
            customerRepository.save(customer);

            Order order = new Order(null, new Date(), business.getTotalAmount(), customer, null);
            orderRepository.save(order);
            for(Article article : business.getCart()){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setArticle(article);
                orderItem.setPrice(article.getPrice());
                orderItem.setQuantity(article.getQuantity());
                orderItemRepository.save(orderItem);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        return "thanks";
    }


}
