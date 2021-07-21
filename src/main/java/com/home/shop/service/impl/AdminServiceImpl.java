package com.home.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.shop.entity.Admin;
import com.home.shop.repository.AdminRepository;
import com.home.shop.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public <S extends Admin> S save(S entity) {
		return adminRepository.save(entity);
	}

	@Override
	public <S extends Admin> Optional<S> findOne(Example<S> example) {
		return adminRepository.findOne(example);
	}

	@Override
	public Page<Admin> findAll(Pageable pageable) {
		return adminRepository.findAll(pageable);
	}

	@Override
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	@Override
	public List<Admin> findAll(Sort sort) {
		return adminRepository.findAll(sort);
	}

	@Override
	public List<Admin> findAllById(Iterable<String> ids) {
		return adminRepository.findAllById(ids);
	}

	@Override
	public Optional<Admin> findById(String id) {
		return adminRepository.findById(id);
	}

	@Override
	public <S extends Admin> List<S> saveAll(Iterable<S> entities) {
		return adminRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		adminRepository.flush();
	}

	@Override
	public <S extends Admin> S saveAndFlush(S entity) {
		return adminRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(String id) {
		return adminRepository.existsById(id);
	}

	@Override
	public <S extends Admin> List<S> saveAllAndFlush(Iterable<S> entities) {
		return adminRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Admin> Page<S> findAll(Example<S> example, Pageable pageable) {
		return adminRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Admin> entities) {
		adminRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Admin> long count(Example<S> example) {
		return adminRepository.count(example);
	}

	@Override
	public <S extends Admin> boolean exists(Example<S> example) {
		return adminRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Admin> entities) {
		adminRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return adminRepository.count();
	}

	@Override
	public void deleteById(String id) {
		adminRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		adminRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Admin entity) {
		adminRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		adminRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		adminRepository.deleteAllInBatch();
	}

	@Override
	public Admin getOne(String id) {
		return adminRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Admin> entities) {
		adminRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		adminRepository.deleteAll();
	}

	@Override
	public Admin getById(String id) {
		return adminRepository.getById(id);
	}

	@Override
	public <S extends Admin> List<S> findAll(Example<S> example) {
		return adminRepository.findAll(example);
	}

	@Override
	public <S extends Admin> List<S> findAll(Example<S> example, Sort sort) {
		return adminRepository.findAll(example, sort);
	}

	@Override
	public boolean checkAdmin(String username, String password) {
		Optional<Admin> opt = findById(username);
		if (opt.isPresent() && opt.get().getPasswordAdmin().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

}
