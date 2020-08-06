package com.example.demo.controller;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.sql.Date;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(CartController.class)
public class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartController cartController;

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

    @Test
    public void testGetCart() throws Exception {
        String uri = "/cart/";
        Cart cart = new Cart();
        long cartId = 123045808;
        Date dateCreated = new Date(Calendar.getInstance().getTime().getTime());
        cart.setId(cartId);
        cart.setDateCreated(dateCreated);

        given(cartController.getCartById(cart.getId())).willReturn(cart);

        mockMvc.perform(MockMvcRequestBuilders.get(uri + cartId)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(((int) cartId))));
    }

    @Test
    public void testCreateCart() throws Exception {
        String uri = "/cart";
        Cart cart = new Cart();
        long cartId = 123456789;
        cart.setId(cartId);

        given(cartController.createCart()).willReturn(cart);

    }

}
