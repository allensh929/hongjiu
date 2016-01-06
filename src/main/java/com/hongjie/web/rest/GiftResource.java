package com.hongjie.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hongjie.domain.Gift;
import com.hongjie.repository.GiftRepository;
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
 * REST controller for managing Gift.
 */
@RestController
@RequestMapping("/api")
public class GiftResource {

    private final Logger log = LoggerFactory.getLogger(GiftResource.class);

    @Inject
    private GiftRepository giftRepository;

    /**
     * POST  /gifts -> Create a new gift.
     */
    @RequestMapping(value = "/gifts",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Gift> createGift(@RequestBody Gift gift) throws URISyntaxException {
        log.debug("REST request to save Gift : {}", gift);
        if (gift.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new gift cannot already have an ID").body(null);
        }
        Gift result = giftRepository.save(gift);
        return ResponseEntity.created(new URI("/api/gifts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("gift", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /gifts -> Updates an existing gift.
     */
    @RequestMapping(value = "/gifts",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Gift> updateGift(@RequestBody Gift gift) throws URISyntaxException {
        log.debug("REST request to update Gift : {}", gift);
        if (gift.getId() == null) {
            return createGift(gift);
        }
        Gift result = giftRepository.save(gift);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("gift", gift.getId().toString()))
            .body(result);
    }

    /**
     * GET  /gifts -> get all the gifts.
     */
    @RequestMapping(value = "/gifts",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Gift>> getAllGifts(Pageable pageable)
        throws URISyntaxException {
        Page<Gift> page = giftRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/gifts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /gifts/:id -> get the "id" gift.
     */
    @RequestMapping(value = "/gifts/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Gift> getGift(@PathVariable Long id) {
        log.debug("REST request to get Gift : {}", id);
        return Optional.ofNullable(giftRepository.findOne(id))
            .map(gift -> new ResponseEntity<>(
                gift,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /gifts/:id -> delete the "id" gift.
     */
    @RequestMapping(value = "/gifts/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteGift(@PathVariable Long id) {
        log.debug("REST request to delete Gift : {}", id);
        giftRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("gift", id.toString())).build();
    }
}
