package com.home.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.home.shop.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

}
