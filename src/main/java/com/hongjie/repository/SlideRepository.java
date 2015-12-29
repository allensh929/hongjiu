package com.hongjie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hongjie.domain.Slide;

/**
 * Spring Data JPA repository for the Slide entity.
 */
public interface SlideRepository extends JpaRepository<Slide,Long> {

	@Query("select s from Slide AS s inner join s.menuPage AS m where s.menuPage.id= ?1 order by s.id asc ")
	public List<Slide> findSlidesByPageId(Long page_id);
}
