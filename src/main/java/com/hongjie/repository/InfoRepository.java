package com.hongjie.repository;

import com.hongjie.domain.Info;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Info entity.
 */
public interface InfoRepository extends JpaRepository<Info,Long> {

}
