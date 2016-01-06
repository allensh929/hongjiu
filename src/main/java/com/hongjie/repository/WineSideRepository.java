package com.hongjie.repository;

import com.hongjie.domain.WineSide;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the WineSide entity.
 */
public interface WineSideRepository extends JpaRepository<WineSide,Long> {

}
