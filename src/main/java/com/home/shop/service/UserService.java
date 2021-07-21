package com.home.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.home.shop.entity.User;

public interface UserService {

	<S extends User> List<S> findAll(Example<S> example, Sort sort);

	<S extends User> List<S> findAll(Example<S> example);

	User getById(Long id);

	void deleteAll();

	void deleteAll(Iterable<? extends User> entities);

	User getOne(Long id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends Long> ids);

	void delete(User entity);

	void deleteAllByIdInBatch(Iterable<Long> ids);

	void deleteById(Long id);

	long count();

	void deleteAllInBatch(Iterable<User> entities);

	<S extends User> boolean exists(Example<S> example);

	<S extends User> long count(Example<S> example);

	void deleteInBatch(Iterable<User> entities);

	<S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(Long id);

	<S extends User> S saveAndFlush(S entity);

	void flush();

	<S extends User> List<S> saveAll(Iterable<S> entities);

	Optional<User> findById(Long id);

	List<User> findAllById(Iterable<Long> ids);

	List<User> findAll(Sort sort);

	List<User> findAll();

	Page<User> findAll(Pageable pageable);

	<S extends User> Optional<S> findOne(Example<S> example);

	<S extends User> S save(S entity);

	Optional<User> findByUsername(String username);
	
	boolean checkLogin(String username, String password);
	
//	List<User> findByNameContaining(String name);
//	
//	Page<User> findByNameContaining(String name, Pageable pageable);

}
