package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;


    @PostMapping
    public Cart createCart() { return cartService.createCart(); }

    @GetMapping(value = "/{id}")
    public Cart getCartById(@PathVariable("id") Long id) {
        return cartService.findCartById(id);
    }



    @DeleteMapping(value = "/{id}")
    public String deleteCartById(@PathVariable("id") Long id) {
        cartService.deleteCartById(id);
        return "Deleted Cart id" + id;
    }

    @PostMapping("/{cartId}/cartItem/{productId}")
    public Cart addCartItem(@PathVariable("cartId") long cartId,
                            @PathVariable("productId") long productId,
                            @RequestParam int qty) {

        Product product = productService.findProductById(productId);
        return cartService.addCartItem(cartId, product, qty);
    }

    @PutMapping("/{cartId}/cartItem/{cartItemId}")
    public Cart updateCartItem(@PathVariable("cartId") long cartId,
                               @PathVariable("cartItemId") long cartItemId,
                               @RequestParam int qty) {
        return  cartService.updateCartItem(cartId, cartItemId, qty);
    }

    @DeleteMapping("/{cartId}/cartItem/{cartItemId}")
    public Cart deleteCartItem(@PathVariable("cartId") long cartId,
                               @PathVariable("cartItemId") long cartItemId)  {
        return cartService.deleteCartItem(cartId, cartItemId);
    }

}
