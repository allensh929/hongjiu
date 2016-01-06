package com.hongjie.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hongjie.domain.Info;
import com.hongjie.repository.InfoRepository;
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
 * REST controller for managing Info.
 */
@RestController
@RequestMapping("/api")
public class InfoResource {

    private final Logger log = LoggerFactory.getLogger(InfoResource.class);

    @Inject
    private InfoRepository infoRepository;

    /**
     * POST  /infos -> Create a new info.
     */
    @RequestMapping(value = "/infos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Info> createInfo(@RequestBody Info info) throws URISyntaxException {
        log.debug("REST request to save Info : {}", info);
        if (info.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new info cannot already have an ID").body(null);
        }
        Info result = infoRepository.save(info);
        return ResponseEntity.created(new URI("/api/infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("info", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /infos -> Updates an existing info.
     */
    @RequestMapping(value = "/infos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Info> updateInfo(@RequestBody Info info) throws URISyntaxException {
        log.debug("REST request to update Info : {}", info);
        if (info.getId() == null) {
            return createInfo(info);
        }
        Info result = infoRepository.save(info);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("info", info.getId().toString()))
            .body(result);
    }

    /**
     * GET  /infos -> get all the infos.
     */
    @RequestMapping(value = "/infos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Info>> getAllInfos(Pageable pageable)
        throws URISyntaxException {
        Page<Info> page = infoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/infos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /infos/:id -> get the "id" info.
     */
    @RequestMapping(value = "/infos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Info> getInfo(@PathVariable Long id) {
        log.debug("REST request to get Info : {}", id);
        return Optional.ofNullable(infoRepository.findOne(id))
            .map(info -> new ResponseEntity<>(
                info,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /infos/:id -> delete the "id" info.
     */
    @RequestMapping(value = "/infos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteInfo(@PathVariable Long id) {
        log.debug("REST request to delete Info : {}", id);
        infoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("info", id.toString())).build();
    }
}
