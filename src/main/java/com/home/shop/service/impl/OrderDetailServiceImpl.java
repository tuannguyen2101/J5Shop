package com.home.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.home.shop.entity.OrderDetail;
import com.home.shop.repository.OrderDetailRepository;
import com.home.shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public <S extends OrderDetail> S save(S entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
		return orderDetailRepository.findOne(example);
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return orderDetailRepository.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return orderDetailRepository.findAll(sort);
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<Integer> ids) {
		return orderDetailRepository.findAllById(ids);
	}

	@Override
	public Optional<OrderDetail> findById(Integer id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return orderDetailRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		orderDetailRepository.flush();
	}

	@Override
	public <S extends OrderDetail> S saveAndFlush(S entity) {
		return orderDetailRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return orderDetailRepository.existsById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderDetailRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderDetailRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetail> entities) {
		orderDetailRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends OrderDetail> long count(Example<S> example) {
		return orderDetailRepository.count(example);
	}

	@Override
	public <S extends OrderDetail> boolean exists(Example<S> example) {
		return orderDetailRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<OrderDetail> entities) {
		orderDetailRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return orderDetailRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		orderDetailRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(OrderDetail entity) {
		orderDetailRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		orderDetailRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		orderDetailRepository.deleteAllInBatch();
	}

	@Override
	public OrderDetail getOne(Integer id) {
		return orderDetailRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		orderDetailRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		orderDetailRepository.deleteAll();
	}

	@Override
	public OrderDetail getById(Integer id) {
		return orderDetailRepository.getById(id);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return orderDetailRepository.findAll(example);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return orderDetailRepository.findAll(example, sort);
	}
	
	
	
}
