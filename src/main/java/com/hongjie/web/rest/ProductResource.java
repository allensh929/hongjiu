package com.hongjie.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
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
import com.hongjie.repository.ProductRepository;
import com.hongjie.web.rest.util.HeaderUtil;
import com.hongjie.web.rest.util.PaginationUtil;

/**
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api")
public class ProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    @Inject
    private ProductRepository productRepository;

    /**
     * POST  /products -> Create a new product.
     */
    @RequestMapping(value = "/products",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to save Product : {}", product);
        if (product.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new product cannot already have an ID").body(null);
        }
        Product result = productRepository.save(product);
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("product", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /products -> Updates an existing product.
     */
    @RequestMapping(value = "/products",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws URISyntaxException {
        log.debug("REST request to update Product : {}", product);
        if (product.getId() == null) {
            return createProduct(product);
        }
        Product result = productRepository.save(product);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("product", product.getId().toString()))
            .body(result);
    }

    /**
     * GET  /products -> get all the products.
     */
    @RequestMapping(value = "/products",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Product>> getAllProducts(Pageable pageable)
        throws URISyntaxException {
        Page<Product> page = productRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/products");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /products/:id -> get the "id" product.
     */
    @RequestMapping(value = "/products/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        return Optional.ofNullable(productRepository.findOne(id))
            .map(product -> new ResponseEntity<>(
                product,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /products/:id -> delete the "id" product.
     */
    @RequestMapping(value = "/products/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        productRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("product", id.toString())).build();
    }
    
    /**
     * GET  /products -> get all the products.
     */
    @RequestMapping(value = "/products/news",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Product>> getAllNewsProducts()
        throws URISyntaxException {
        List<Product> news = productRepository.findAllNewsProducts();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
    
    /**
     * GET  /products -> get all the products.
     */
    @RequestMapping(value = "/products/favo",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Product>> getAllFavoProducts()
        throws URISyntaxException {
        List<Product> news = productRepository.findAllFavoProducts();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
    
    /**
     * GET  /products -> search the products.
     */
    @RequestMapping(value = "/products/search/{search}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Product>> getSearchProducts(@PathVariable String search)
        throws URISyntaxException {
        List<Product> news = productRepository.findAllSearchProducts(search);
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
    
    
    /**
     * GET  /products -> get all the products by regions.
     */
    @RequestMapping(value = "/products/byregions",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Map<String,List<Product>>> getByRegionsProducts()
        throws URISyntaxException {
        List<Product> products = productRepository.findByRegionsProducts();
        Map<String,List<Product>> result = new HashMap<String,List<Product>>();
        
        if(products!= null && products.size() > 0){
        	for(Product p : products){
        		if (StringUtils.isEmpty(p.getOriginCountry())){
    				p.setOriginCountry("其他");
    			}
        		if (!result.containsKey(p.getOriginCountry())){
        			List<Product> list = new ArrayList<Product>();
        			list.add(p);
        			result.put(p.getOriginCountry(), list);
        		}else{
        			List<Product> list = result.get(p.getOriginCountry());
        			list.add(p);
        		}
        	}
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    /**
     * GET  /products -> get all the products by variety.
     */
    @RequestMapping(value = "/products/byvariety",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Map<String,List<Product>>> getByVarietyProducts()
        throws URISyntaxException {
        List<Product> products = productRepository.findByVarietyProducts();
        Map<String,List<Product>> result = new HashMap<String,List<Product>>();
        
        if(products!= null && products.size() > 0){
        	for(Product p : products){
        		if (StringUtils.isEmpty(p.getVariety())){
    				p.setVariety("其他");
    			}
        		if (!result.containsKey(p.getVariety())){
        			List<Product> list = new ArrayList<Product>();
        			list.add(p);
        			result.put(p.getVariety(), list);
        		}else{
        			List<Product> list = result.get(p.getVariety());
        			list.add(p);
        		}
        	}
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    /**
     * PUT  /products/:id/favo -> favor the "id" product.
     */
    @RequestMapping(value = "/products/{id}/favo",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Product> favoProduct(@PathVariable Long id) {
        log.debug("REST request to favo Product : {}", id);
        
        Product product = productRepository.findOne(id);
        Product result = null;
        if (product.getId() != null) {
            product.setFavorate(product.getFavorate() == null ? 1 : product.getFavorate() + 1);
            result = productRepository.save(product);
        }
       
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("product", product.getId().toString()))
            .body(result);
    }
}
