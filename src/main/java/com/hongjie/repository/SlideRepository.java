package com.hongjie.repository;

import com.hongjie.domain.Slide;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Slide entity.
 */
public interface SlideRepository extends JpaRepository<Slide,Long> {

}
