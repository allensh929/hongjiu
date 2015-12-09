package com.hongjie.repository;

import com.hongjie.domain.ProductRelate;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ProductRelate entity.
 */
public interface ProductRelateRepository extends JpaRepository<ProductRelate,Long> {

}
