package com.example.demo.service;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemRepository cartItemRepository;

    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    public Cart findCartById(long id) {
        Cart cart = cartRepository.findById(id).orElseThrow( () ->
                new NotFoundException(String.format("Cart %d not found", id)));
        updateCartItems(cart);
        updateCartTotal(cart);
        return cart;
    }

    public long deleteCartById(long id) {
        cartRepository.deleteById(id);
        return id;
    }

    public CartItem findCartItem(long cartItemId, long cartId) {
        CartItem cartItem =  cartItemRepository.findById(cartItemId).orElseThrow(() ->
                new NotFoundException(String.format("Cart %d not found", cartItemId)));

        if(cartItem.getCartId() != cartId) {
            throw new NotFoundException(String.format("Cart %d does not contain cart item %d", cartId, cartItemId));
        }
        return cartItem;
    }

    public Cart addCartItem(long cartId, Product product, int qty) {
        Cart cart = findCartById(cartId);
        CartItem newCartItem = new CartItem(cart, product, qty);
        cart = cartItemRepository.save(newCartItem).getCart();
        updateCartItems(cart);
        updateCartTotal(cart);

        return updateCartTotal(cart);
    }

    public Cart updateCartItem(long cartId, long cartItemId, int qty) {
        CartItem cartItem = findCartItem(cartItemId, cartId);
        Cart updatedCart = findCartById(cartId);
        if(qty == 0) {
            deleteCartItem(cartId, cartItemId);
        } else {
            cartItem.setQty(qty);
            cartItemRepository.save(cartItem);
        }

        updateCartItems(updatedCart);
        return updateCartTotal(updatedCart);
    }

    public Cart deleteCartItem(long cartId, long cartItemId) {
        CartItem cartItem = findCartItem(cartItemId, cartId);
        long cartItemCartId = cartItem.getCartId();
        cartItemRepository.deleteById(cartItemId);
        Cart updatedCart = findCartById(cartItemCartId);
        updateCartItems(updatedCart);

        return updateCartTotal(updatedCart);
    }

    private Cart updateCartItems(Cart cart) {
        Set<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
        cart.setCartItems(cartItems);
        return cart;
    }

    private Cart updateCartTotal(Cart cart) {
        double sum = 0.00;
        Set<CartItem> cartItems = cart.getCartItems();
        if(!cartItems.isEmpty()) {
            for (CartItem cartItem : cartItems) {
                sum += cartItem.getTotalPrice();
            }
        }
        cart.setCartTotal(sum);
        return cart;
    }
}

