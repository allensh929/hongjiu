package com.hongjie.repository;

import com.hongjie.domain.MenuPage;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MenuPage entity.
 */
public interface MenuPageRepository extends JpaRepository<MenuPage,Long> {

}
