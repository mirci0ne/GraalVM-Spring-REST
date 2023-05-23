package org.check.something.services;

import lombok.RequiredArgsConstructor;
import org.check.something.entities.Product;
import org.check.something.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        product.setLastUpdate(new java.sql.Date(new java.util.Date().getTime()));
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product product) {
        product.setLastUpdate(new java.sql.Date(new java.util.Date().getTime()));
        product.setId(id);
        product.setProductName(product.getProductName());
        product.setStock(product.getStock());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
