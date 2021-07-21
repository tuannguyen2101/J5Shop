package com.home.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.shop.entity.Category;
import com.home.shop.repository.CategoryRepository;
import com.home.shop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> findByNameContaining(String name) {
		return categoryRepository.findByNameContaining(name);
	}
	
	@Override
	public Page<Category> findByNameContaining(String name, Pageable pageable) {
		return categoryRepository.findByNameContaining(name, pageable);
	}

	@Override
	public <S extends Category> S save(S entity) {
		return categoryRepository.save(entity);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}

	@Override
	public List<Category> findAllById(Iterable<Long> ids) {
		return categoryRepository.findAllById(ids);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return categoryRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		categoryRepository.flush();
	}

	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return categoryRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Long id) {
		return categoryRepository.existsById(id);
	}

	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryRepository.saveAllAndFlush(entities);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void deleteInBatch(Iterable<Category> entities) {
		categoryRepository.deleteInBatch(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Category> entities) {
		categoryRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		categoryRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		categoryRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		categoryRepository.deleteAllInBatch();
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		categoryRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

	@Override
	public Category getById(Long id) {
		return categoryRepository.getById(id);
	}

}
