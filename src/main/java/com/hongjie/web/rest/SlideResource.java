package com.hongjie.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hongjie.domain.Slide;
import com.hongjie.repository.SlideRepository;
import com.hongjie.web.rest.util.HeaderUtil;
import com.hongjie.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Slide.
 */
@RestController
@RequestMapping("/api")
public class SlideResource {

    private final Logger log = LoggerFactory.getLogger(SlideResource.class);

    @Inject
    private SlideRepository slideRepository;

    /**
     * POST  /slides -> Create a new slide.
     */
    @RequestMapping(value = "/slides",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Slide> createSlide(@RequestBody Slide slide) throws URISyntaxException {
        log.debug("REST request to save Slide : {}", slide);
        if (slide.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new slide cannot already have an ID").body(null);
        }
        Slide result = slideRepository.save(slide);
        return ResponseEntity.created(new URI("/api/slides/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("slide", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /slides -> Updates an existing slide.
     */
    @RequestMapping(value = "/slides",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Slide> updateSlide(@RequestBody Slide slide) throws URISyntaxException {
        log.debug("REST request to update Slide : {}", slide);
        if (slide.getId() == null) {
            return createSlide(slide);
        }
        Slide result = slideRepository.save(slide);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("slide", slide.getId().toString()))
            .body(result);
    }

    /**
     * GET  /slides -> get all the slides.
     */
    @RequestMapping(value = "/slides",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Slide>> getAllSlides(Pageable pageable)
        throws URISyntaxException {
        Page<Slide> page = slideRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/slides");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /slides/:id -> get the "id" slide.
     */
    @RequestMapping(value = "/slides/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Slide> getSlide(@PathVariable Long id) {
        log.debug("REST request to get Slide : {}", id);
        return Optional.ofNullable(slideRepository.findOne(id))
            .map(slide -> new ResponseEntity<>(
                slide,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /slides/:id -> delete the "id" slide.
     */
    @RequestMapping(value = "/slides/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSlide(@PathVariable Long id) {
        log.debug("REST request to delete Slide : {}", id);
        slideRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("slide", id.toString())).build();
    }
}
