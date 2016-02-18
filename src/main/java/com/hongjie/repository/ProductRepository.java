package com.hongjie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

	@Query("select p from Product AS p order by p.variety desc ")
	public List<Product> findByVarietyProducts();

	@Query("select p from Product AS p order by p.occasion desc ")
    public List<Product> findByOccasionProducts();
	
	@Query(value = "select * from product where concat(ifnull(name,''), ifnull(title,''), ifnull(origin_country,''), ifnull(types,''), ifnull(variety,''), ifnull(zone,''), ifnull(occasion,''), ifnull(tag,'')) like %:search% order by number desc ", nativeQuery = true)
	public List<Product> findAllSearchProducts(@Param("search") String search);
}
