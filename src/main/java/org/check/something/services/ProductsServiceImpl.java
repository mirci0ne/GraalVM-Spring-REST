package org.check.something.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.check.something.entities.Product;
import org.check.something.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductsServiceImpl {

    private final ProductRepository productRepository;

    public Product save(Product product) {
        log.info("Saving product");
        product.setLastUpdate(new java.sql.Date(new java.util.Date().getTime()));
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(Long id) {
        log.info("Returning product for id: {}", id);
        return productRepository.findById(id);
    }

    public List<Product> getProducts() {
        log.info("Returning products");
        return productRepository.findAll();
    }

    public Product updateProduct(Long id, Product product) {
        log.info("Updating product for id: {}", id);
        product.setLastUpdate(new java.sql.Date(new java.util.Date().getTime()));
        product.setId(id);
        product.setProductName(product.getProductName());
        product.setStock(product.getStock());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        log.info("Deleting product for id: {}", id);
        productRepository.deleteById(id);
    }

}
