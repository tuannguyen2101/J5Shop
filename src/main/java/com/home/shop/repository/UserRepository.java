package com.home.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.shop.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
//	List<User> findByNameContaining(String name);
//	Page<User> findByNameContaining(String name, Pageable pageable);
}
