package com.zemoga.portfolies.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zemoga.portfolies.rest.api.entity.model.Portfolio;

/**
 * Class to invoke the database operations.
 * 
 * @author OMAR ROSAS-PC
 *
 */
@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
	Iterable<Portfolio> findByTittleContaining(String tittle);

}
