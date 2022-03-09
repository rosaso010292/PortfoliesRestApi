package com.zemoga.portfolies.rest.api.service;

import java.util.Optional;

import com.zemoga.portfolies.rest.api.entity.model.Portfolio;

/**
 * Interface class to define the service operations.
 * 
 * @author OMAR ROSAS-PC
 * @version 1.0
 */
public interface PortfolioService {

	/**
	 * Find all portfolies registered.
	 * 
	 * @return List of portfolies.
	 */
	Iterable<Portfolio> findAll();

	/**
	 * Find all portfolies that are equals to input tittle.
	 * 
	 * @return List of portfolies.
	 */
	Iterable<Portfolio> findByTittle(String tittle);

	/**
	 * Find by id the portfolio data.
	 * 
	 * @param id Portfolio id.
	 * @return Portfolio current data.
	 */
	Optional<Portfolio> findById(Long id);

	/**
	 * Save and update portfolio data.
	 * 
	 * @param portfolio Portfolio data to save
	 * @return Portfolio data saved.
	 */
	Portfolio save(Portfolio portfolio);
}
