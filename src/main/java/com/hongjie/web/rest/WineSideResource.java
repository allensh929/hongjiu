package com.hongjie.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hongjie.domain.WineSide;
import com.hongjie.repository.WineSideRepository;
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
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WineSide.
 */
@RestController
@RequestMapping("/api")
public class WineSideResource {

    private final Logger log = LoggerFactory.getLogger(WineSideResource.class);

    @Inject
    private WineSideRepository wineSideRepository;

    /**
     * POST  /wineSides -> Create a new wineSide.
     */
    @RequestMapping(value = "/wineSides",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WineSide> createWineSide(@Valid @RequestBody WineSide wineSide) throws URISyntaxException {
        log.debug("REST request to save WineSide : {}", wineSide);
        if (wineSide.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new wineSide cannot already have an ID").body(null);
        }
        WineSide result = wineSideRepository.save(wineSide);
        return ResponseEntity.created(new URI("/api/wineSides/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("wineSide", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wineSides -> Updates an existing wineSide.
     */
    @RequestMapping(value = "/wineSides",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WineSide> updateWineSide(@Valid @RequestBody WineSide wineSide) throws URISyntaxException {
        log.debug("REST request to update WineSide : {}", wineSide);
        if (wineSide.getId() == null) {
            return createWineSide(wineSide);
        }
        WineSide result = wineSideRepository.save(wineSide);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("wineSide", wineSide.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wineSides -> get all the wineSides.
     */
    @RequestMapping(value = "/wineSides",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<WineSide>> getAllWineSides(Pageable pageable)
        throws URISyntaxException {
        Page<WineSide> page = wineSideRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/wineSides");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /wineSides/:id -> get the "id" wineSide.
     */
    @RequestMapping(value = "/wineSides/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WineSide> getWineSide(@PathVariable Long id) {
        log.debug("REST request to get WineSide : {}", id);
        return Optional.ofNullable(wineSideRepository.findOne(id))
            .map(wineSide -> new ResponseEntity<>(
                wineSide,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /wineSides/:id -> delete the "id" wineSide.
     */
    @RequestMapping(value = "/wineSides/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWineSide(@PathVariable Long id) {
        log.debug("REST request to delete WineSide : {}", id);
        wineSideRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("wineSide", id.toString())).build();
    }
}
