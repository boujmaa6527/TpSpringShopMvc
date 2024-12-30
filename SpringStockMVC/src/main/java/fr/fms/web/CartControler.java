package fr.fms.web;

import fr.fms.entities.Cart;
import fr.fms.entities.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("cart")
public class CartControler {

        @ModelAttribute("cart")
        public Cart cart(){
            return new Cart();
        }
        @GetMapping("/articles")
        public String showArticle(){
            return "articles";
        }
        @PostMapping("/cart/add")
        public String addCart(@ModelAttribute Cart cart,
                              @RequestParam Long productId,
                              @RequestParam int quantity){
            cart.addItem((new CartItem(productId, quantity)));
            return "redirect:/cart";
        }
        @PostMapping("cart/remove")
        public String removeFromCart(@ModelAttribute Cart cart,
                                     @RequestParam Long productId){
            cart.removeItem(productId);
            return "redirect:/cart";
        }


}
