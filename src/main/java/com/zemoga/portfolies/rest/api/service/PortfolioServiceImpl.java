package com.zemoga.portfolies.rest.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zemoga.portfolies.rest.api.entity.model.Portfolio;
import com.zemoga.portfolies.rest.api.repository.PortfolioRepository;

/**
 * Class that implements the service operations.
 * 
 * @author OMAR ROSAS-PC
 *
 */
@Service
public class PortfolioServiceImpl implements PortfolioService {
	@Autowired
	private PortfolioRepository repository;

	@Transactional(readOnly = true)
	public Iterable<Portfolio> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Iterable<Portfolio> findByTittle(String tittle) {
		return repository.findByTittleContaining(tittle);
	}

	@Transactional(readOnly = true)
	public Optional<Portfolio> findById(Long id) {
		return repository.findById(id);
	}

	@Transactional()
	public Portfolio save(Portfolio portfolio) {
		return repository.save(portfolio);
	}

}
