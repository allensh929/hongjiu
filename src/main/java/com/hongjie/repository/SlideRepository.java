package com.hongjie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hongjie.domain.Slide;

/**
 * Spring Data JPA repository for the Slide entity.
 */
public interface SlideRepository extends JpaRepository<Slide,Long> {

}
