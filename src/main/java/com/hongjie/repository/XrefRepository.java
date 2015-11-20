package com.hongjie.repository;

import com.hongjie.domain.Xref;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Xref entity.
 */
public interface XrefRepository extends JpaRepository<Xref,Long> {

}
