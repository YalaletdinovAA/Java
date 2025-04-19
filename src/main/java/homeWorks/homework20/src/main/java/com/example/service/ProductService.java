package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(id));
        if (product.isEmpty()) {
            throw new RuntimeException("Продукт с ID " + id + " не найден");
        }
        return product.get();
    }
    public Product updateProduct(Long id, Product product) {
        product.setId(id); // Устанавливаем ID из пути запроса
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}