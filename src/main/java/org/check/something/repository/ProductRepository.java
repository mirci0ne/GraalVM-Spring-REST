package org.check.something.repository;

import org.check.something.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from product where product_name = :productName", nativeQuery = true)
    Product getIdByProductName(@Param("productName") String productName);

}
