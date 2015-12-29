package com.hongjie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hongjie.domain.MenuPage;

/**
 * Spring Data JPA repository for the MenuPage entity.
 */
public interface MenuPageRepository extends JpaRepository<MenuPage,Long> {

	
	@Query("select p from MenuPage AS p where p.active = 1 order by p.orderTag asc ")
	public List<MenuPage> findAllActiveMenuPages();
}
