package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceImplTest {

    @Mock
    CartRepository cartRepository;

    @Mock
    CartItemRepository cartItemRepository;

    @InjectMocks
    CartServiceImpl cartService;


    @Test
    public void testCreateCart() {
        String uri = "/cart/";
        Cart cart = new Cart();
        long cartId = 123045808;
        Date dateCreated = new Date(Calendar.getInstance().getTime().getTime());
        cart.setId(cartId);
        cart.setDateCreated(dateCreated);

        given(cartService.createCart()).willReturn(cart);

        Cart testCart = cartService.createCart();

        assertThat(testCart.getId()).isEqualTo(cartId);
    }

    @Test
    public void testGetCartById(){
        String uri = "/cart/";
        Cart cart = new Cart();
        long cartId = 123045808;
        Date dateCreated = new Date(Calendar.getInstance().getTime().getTime());
        cart.setId(cartId);
        cart.setDateCreated(dateCreated);

        given(cartService.findCartById(cartId)).willReturn(cart);

        Cart testCart = cartService.findCartById(cartId);
        assertThat(testCart.getId()).isEqualTo(cartId);
    }

    @Test
    public void testClearCart(){

    }

    long cartId = 123045808;
    Cart testCart;
    Product productOne;
    Product productTwo;

    @Before
    public void setUp() {
        double price = 10.00;
        productOne = new Product("TestProductOne", price, "www.testProductOneImg.com");

        double price2 = 100.00;
        productTwo = new Product("TestProductTwo", price2, "www.testProductTwoImg.com");

        testCart = new Cart();
        testCart.setId(cartId);
    }


}
