package com.example.demo.service;

import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {

    Product addProduct(Product newProduct);
    void updateProduct(Product product);
    void deleteProduct(long id);
    List<Product> findAllProducts();
    Product findProductById(long id);
}
