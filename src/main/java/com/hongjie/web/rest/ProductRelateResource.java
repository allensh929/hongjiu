package com.hongjie.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.hongjie.domain.Product;
import com.hongjie.domain.ProductRelate;
import com.hongjie.repository.ProductRelateRepository;
import com.hongjie.web.rest.util.HeaderUtil;
import com.hongjie.web.rest.util.PaginationUtil;

/**
 * REST controller for managing ProductRelate.
 */
@RestController
@RequestMapping("/api")
public class ProductRelateResource {

    private final Logger log = LoggerFactory.getLogger(ProductRelateResource.class);

    @Inject
    private ProductRelateRepository productRelateRepository;

    /**
     * POST  /productRelates -> Create a new productRelate.
     */
    @RequestMapping(value = "/productRelates",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProductRelate> createProductRelate(@RequestBody ProductRelate productRelate) throws URISyntaxException {
        log.debug("REST request to save ProductRelate : {}", productRelate);
        if (productRelate.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new productRelate cannot already have an ID").body(null);
        }
        ProductRelate result = productRelateRepository.save(productRelate);
        return ResponseEntity.created(new URI("/api/productRelates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("productRelate", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /productRelates -> Updates an existing productRelate.
     */
    @RequestMapping(value = "/productRelates",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProductRelate> updateProductRelate(@RequestBody ProductRelate productRelate) throws URISyntaxException {
        log.debug("REST request to update ProductRelate : {}", productRelate);
        if (productRelate.getId() == null) {
            return createProductRelate(productRelate);
        }
        ProductRelate result = productRelateRepository.save(productRelate);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("productRelate", productRelate.getId().toString()))
            .body(result);
    }

    /**
     * GET  /productRelates -> get all the productRelates.
     */
    @RequestMapping(value = "/productRelates",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ProductRelate>> getAllProductRelates(Pageable pageable)
        throws URISyntaxException {
        Page<ProductRelate> page = productRelateRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/productRelates");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /productRelates/:id -> get the "id" productRelate.
     */
    @RequestMapping(value = "/productRelates/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ProductRelate> getProductRelate(@PathVariable Long id) {
        log.debug("REST request to get ProductRelate : {}", id);
        return Optional.ofNullable(productRelateRepository.findOne(id))
            .map(productRelate -> new ResponseEntity<>(
                productRelate,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /productRelates/:id -> delete the "id" productRelate.
     */
    @RequestMapping(value = "/productRelates/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProductRelate(@PathVariable Long id) {
        log.debug("REST request to delete ProductRelate : {}", id);
        productRelateRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("productRelate", id.toString())).build();
    }
    
    /**
     * GET  /productRelates -> get all the productRelates.
     */
    @RequestMapping(value = "/{product_id}/productRelates",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Product>> getProductRelatesByProductId(@PathVariable Long product_id)
        throws URISyntaxException {
        List<Product> relates = productRelateRepository.findProductRelatesByProductId(product_id);
        return new ResponseEntity<>(relates, HttpStatus.OK);
    }
}
