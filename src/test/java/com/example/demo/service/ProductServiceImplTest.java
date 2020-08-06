package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    long productOneId = 123456789;
    Product productOne;

    @Before
    public void testSetup() {
        double price = 10.00;
        productOne = new Product("TestProduct", price, "www.testProductImg.com");
    }

    @Test
    public void testGetProducts() {
        List<Product> allProducts = singletonList(productOne);

        given(productRepository.findAll()).willReturn(allProducts);

        List<Product> products = productService.findAllProducts();

        assertThat(products.size()).isEqualTo(1);
        assertThat(products.get(0).getName()).isEqualTo(productOne.getName());
    }

    @Test
    public void testFindProductById() {
        productOne.setId(productOneId);
        given(productRepository.findById(productOneId)).willReturn(Optional.ofNullable(productOne));

        Optional<Product> testProduct = productRepository.findById(productOneId);
        assertThat(testProduct.get().getId()).isEqualTo(productOneId);
    }

}
