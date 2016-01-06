package com.hongjie.repository;

import com.hongjie.domain.Gift;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Gift entity.
 */
public interface GiftRepository extends JpaRepository<Gift,Long> {

}
