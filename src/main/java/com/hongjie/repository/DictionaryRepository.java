package com.hongjie.repository;

import com.hongjie.domain.Dictionary;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Dictionary entity.
 */
public interface DictionaryRepository extends JpaRepository<Dictionary,Long> {

}
