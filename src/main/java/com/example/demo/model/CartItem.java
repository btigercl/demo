package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@Table(name = "CartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @JoinColumn(name = "product_id", nullable=false)
    private long productId;

    @Column(name = "qty")
    private int qty;

    @Column(name = "cart_Id")
    private long cartId;

    @Transient
    @JsonProperty
    @JsonBackReference
    @ManyToOne
    private Cart cart;

    @Column(name = "image_url")
    private String imageUrl;


    @Column (name = "name")
    private String productName;

    @Transient
    @JsonIgnore
    @Column (name = "product")
    private Product product;

    @Column (name = "price")
    private Double price;


    public double getTotalPrice() {
        return this.qty * this.price;
    }

    public CartItem() {}

    public CartItem(Cart cart, Product product, int qty) {
        this.cart = cart;
        this.cartId = cart.getId();
        this.productId = product.getId();
        this.qty = qty;
        this.product = product;
        this.productName = product.getName();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
    }
}
