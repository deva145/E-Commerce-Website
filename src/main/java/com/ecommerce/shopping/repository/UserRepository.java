package com.ecommerce.shopping.repository;

import com.ecommerce.shopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
