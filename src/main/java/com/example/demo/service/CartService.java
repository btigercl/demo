package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;


public interface CartService {

    Cart createCart();
    Cart findCartById(long id);
    long deleteCartById(long id);
    Cart addCartItem(long cartId, Product product, int qty);
    Cart updateCartItem(long cartId, long cartItemId, int qty);
    Cart deleteCartItem(long cartId, long cartItemId);

}
