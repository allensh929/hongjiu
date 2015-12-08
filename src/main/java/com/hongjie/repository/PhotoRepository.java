package com.hongjie.repository;

import com.hongjie.domain.Photo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Photo entity.
 */
public interface PhotoRepository extends JpaRepository<Photo,Long> {

}
