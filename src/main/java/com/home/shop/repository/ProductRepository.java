package com.home.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.shop.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findByNameContaining(String name);
	Page<Product> findByNameContaining(String name, Pageable pageable);
}
