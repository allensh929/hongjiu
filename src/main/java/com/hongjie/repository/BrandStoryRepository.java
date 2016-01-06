package com.hongjie.repository;

import com.hongjie.domain.BrandStory;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BrandStory entity.
 */
public interface BrandStoryRepository extends JpaRepository<BrandStory,Long> {

}
