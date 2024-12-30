package fr.fms.entities;

import java.util.ArrayList;
import java.util.List;

public class Cart {

        private List<CartItem> items = new ArrayList<>();


        public void addItem(CartItem cartItem){
            items.add(cartItem);
        }
        public void removeItem(Long productId){
            items.removeIf(item ->item.getProductId().equals(productId));
        }
        public List<CartItem> getItems(){
            return items;
        }

    public void ajouterArticle(String article) {

    }
}
