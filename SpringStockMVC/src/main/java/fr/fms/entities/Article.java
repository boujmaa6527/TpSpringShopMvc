package fr.fms.entities;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
public class Article implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marque;
    @NotNull
    @Size(min = 10, max = 50)
    private String description;

    @DecimalMin("50")
    private double price;
    


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Article(Long id, String marque, String description, double price, Category category) {
        this.id = id;
        this.marque = marque;
        this.description = description;
        this.price = price;
        this.category = category;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getMarque() { return marque; }

    public Article(String marque, String description, double price, Category category) {
        this.marque = marque;
        this.description = description;
        this.price = price;
        this.category = category;
    }


    public Article(){}

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
