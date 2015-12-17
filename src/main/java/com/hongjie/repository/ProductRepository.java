package com.hongjie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hongjie.domain.Product;

/**
 * Spring Data JPA repository for the Product entity.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p from Product AS p where p.news = 1 order by p.number desc ")
	public List<Product> findAllNewsProducts();
	
	@Query("select p from Product AS p where p.favo = 1 order by p.favorate desc ")
	public List<Product> findAllFavoProducts();
	
	@Query("select p from Product AS p order by p.originCountry desc ")
	public List<Product> findByRegionsProducts();
}
