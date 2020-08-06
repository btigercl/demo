package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.exceptions.*;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product addProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(long id) throws NotFoundException{
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Product %d not found", id)));
    }
}
