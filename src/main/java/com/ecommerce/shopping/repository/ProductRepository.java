package com.ecommerce.shopping.repository;

import com.ecommerce.shopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
