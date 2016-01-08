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
import com.hongjie.domain.BrandStory;
import com.hongjie.repository.BrandStoryRepository;
import com.hongjie.web.rest.util.HeaderUtil;
import com.hongjie.web.rest.util.PaginationUtil;

/**
 * REST controller for managing BrandStory.
 */
@RestController
@RequestMapping("/api")
public class BrandStoryResource {

    private final Logger log = LoggerFactory.getLogger(BrandStoryResource.class);

    @Inject
    private BrandStoryRepository brandStoryRepository;

    /**
     * POST  /brandStorys -> Create a new brandStory.
     */
    @RequestMapping(value = "/brandStorys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BrandStory> createBrandStory(@RequestBody BrandStory brandStory) throws URISyntaxException {
        log.debug("REST request to save BrandStory : {}", brandStory);
        if (brandStory.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new brandStory cannot already have an ID").body(null);
        }
        BrandStory result = brandStoryRepository.save(brandStory);
        return ResponseEntity.created(new URI("/api/brandStorys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("brandStory", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /brandStorys -> Updates an existing brandStory.
     */
    @RequestMapping(value = "/brandStorys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BrandStory> updateBrandStory(@RequestBody BrandStory brandStory) throws URISyntaxException {
        log.debug("REST request to update BrandStory : {}", brandStory);
        if (brandStory.getId() == null) {
            return createBrandStory(brandStory);
        }
        BrandStory result = brandStoryRepository.save(brandStory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("brandStory", brandStory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /brandStorys -> get all the brandStorys.
     */
    @RequestMapping(value = "/brandStorys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<BrandStory>> getAllBrandStorys(Pageable pageable)
        throws URISyntaxException {
        Page<BrandStory> page = brandStoryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/brandStorys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /brandStorys/:id -> get the "id" brandStory.
     */
    @RequestMapping(value = "/brandStorys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BrandStory> getBrandStory(@PathVariable Long id) {
        log.debug("REST request to get BrandStory : {}", id);
        return Optional.ofNullable(brandStoryRepository.findOne(id))
            .map(brandStory -> new ResponseEntity<>(
                brandStory,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /brandStorys/:id -> delete the "id" brandStory.
     */
    @RequestMapping(value = "/brandStorys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBrandStory(@PathVariable Long id) {
        log.debug("REST request to delete BrandStory : {}", id);
        brandStoryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("brandStory", id.toString())).build();
    }
    
    /**
     * GET  /brandStorys/active -> get all the active story.
     */
    @RequestMapping(value = "/brandStorys/active",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<BrandStory>> getAllActiveStorys()
        throws URISyntaxException {
        List<BrandStory> news = brandStoryRepository.findAllActiveStorys();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
