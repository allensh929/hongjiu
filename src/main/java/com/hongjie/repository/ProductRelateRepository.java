package com.hongjie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hongjie.domain.Product;
import com.hongjie.domain.ProductRelate;

/**
 * Spring Data JPA repository for the ProductRelate entity.
 */
public interface ProductRelateRepository extends JpaRepository<ProductRelate,Long> {

	@Query("select p from ProductRelate AS pr inner join pr.relateProduct AS p where pr.product.id= ?1 order by p.id asc ")
	public List<Product> findProductRelatesByProductId(Long product_id);
}
