package com.hongjie.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hongjie.domain.Xref;
import com.hongjie.repository.XrefRepository;
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
 * REST controller for managing Xref.
 */
@RestController
@RequestMapping("/api")
public class XrefResource {

    private final Logger log = LoggerFactory.getLogger(XrefResource.class);

    @Inject
    private XrefRepository xrefRepository;

    /**
     * POST  /xrefs -> Create a new xref.
     */
    @RequestMapping(value = "/xrefs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Xref> createXref(@RequestBody Xref xref) throws URISyntaxException {
        log.debug("REST request to save Xref : {}", xref);
        if (xref.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new xref cannot already have an ID").body(null);
        }
        Xref result = xrefRepository.save(xref);
        return ResponseEntity.created(new URI("/api/xrefs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("xref", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /xrefs -> Updates an existing xref.
     */
    @RequestMapping(value = "/xrefs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Xref> updateXref(@RequestBody Xref xref) throws URISyntaxException {
        log.debug("REST request to update Xref : {}", xref);
        if (xref.getId() == null) {
            return createXref(xref);
        }
        Xref result = xrefRepository.save(xref);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("xref", xref.getId().toString()))
            .body(result);
    }

    /**
     * GET  /xrefs -> get all the xrefs.
     */
    @RequestMapping(value = "/xrefs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Xref>> getAllXrefs(Pageable pageable)
        throws URISyntaxException {
        Page<Xref> page = xrefRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/xrefs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /xrefs/:id -> get the "id" xref.
     */
    @RequestMapping(value = "/xrefs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Xref> getXref(@PathVariable Long id) {
        log.debug("REST request to get Xref : {}", id);
        return Optional.ofNullable(xrefRepository.findOne(id))
            .map(xref -> new ResponseEntity<>(
                xref,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /xrefs/:id -> delete the "id" xref.
     */
    @RequestMapping(value = "/xrefs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteXref(@PathVariable Long id) {
        log.debug("REST request to delete Xref : {}", id);
        xrefRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("xref", id.toString())).build();
    }
}
