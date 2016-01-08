package com.hongjie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hongjie.domain.BrandStory;

/**
 * Spring Data JPA repository for the BrandStory entity.
 */
public interface BrandStoryRepository extends JpaRepository<BrandStory,Long> {

	@Query("select b from BrandStory AS b where b.active = 1 order by b.orderTag asc ")
	public List<BrandStory> findAllActiveStorys();
}
