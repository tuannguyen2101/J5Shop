package com.home.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.home.shop.entity.Admin;

public interface AdminService {

	<S extends Admin> List<S> findAll(Example<S> example, Sort sort);

	<S extends Admin> List<S> findAll(Example<S> example);

	Admin getById(String id);

	void deleteAll();

	void deleteAll(Iterable<? extends Admin> entities);

	Admin getOne(String id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends String> ids);

	void delete(Admin entity);

	void deleteAllByIdInBatch(Iterable<String> ids);

	void deleteById(String id);

	long count();

	void deleteAllInBatch(Iterable<Admin> entities);

	<S extends Admin> boolean exists(Example<S> example);

	<S extends Admin> long count(Example<S> example);

	void deleteInBatch(Iterable<Admin> entities);

	<S extends Admin> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Admin> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(String id);

	<S extends Admin> S saveAndFlush(S entity);

	void flush();

	<S extends Admin> List<S> saveAll(Iterable<S> entities);

	Optional<Admin> findById(String id);

	List<Admin> findAllById(Iterable<String> ids);

	List<Admin> findAll(Sort sort);

	List<Admin> findAll();

	Page<Admin> findAll(Pageable pageable);

	<S extends Admin> Optional<S> findOne(Example<S> example);

	<S extends Admin> S save(S entity);
	
	boolean checkAdmin(String username, String password);

	boolean isAdmin();

}
