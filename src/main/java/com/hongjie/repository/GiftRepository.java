package com.hongjie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hongjie.domain.Gift;

/**
 * Spring Data JPA repository for the Gift entity.
 */
public interface GiftRepository extends JpaRepository<Gift,Long> {

	@Query("select g from Gift AS g where g.active = 1 order by g.orderTag asc ")
	public List<Gift> findAllActiveGifts();
}
