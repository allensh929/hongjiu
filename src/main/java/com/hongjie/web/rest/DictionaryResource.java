package com.hongjie.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hongjie.domain.Dictionary;
import com.hongjie.repository.DictionaryRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Dictionary.
 */
@RestController
@RequestMapping("/api")
public class DictionaryResource {

    private final Logger log = LoggerFactory.getLogger(DictionaryResource.class);

    @Inject
    private DictionaryRepository dictionaryRepository;

    /**
     * POST  /dictionarys -> Create a new dictionary.
     */
    @RequestMapping(value = "/dictionarys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Dictionary> createDictionary(@RequestBody Dictionary dictionary) throws URISyntaxException {
        log.debug("REST request to save Dictionary : {}", dictionary);
        if (dictionary.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new dictionary cannot already have an ID").body(null);
        }
        Dictionary result = dictionaryRepository.save(dictionary);
        return ResponseEntity.created(new URI("/api/dictionarys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("dictionary", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dictionarys -> Updates an existing dictionary.
     */
    @RequestMapping(value = "/dictionarys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Dictionary> updateDictionary(@RequestBody Dictionary dictionary) throws URISyntaxException {
        log.debug("REST request to update Dictionary : {}", dictionary);
        if (dictionary.getId() == null) {
            return createDictionary(dictionary);
        }
        Dictionary result = dictionaryRepository.save(dictionary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("dictionary", dictionary.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dictionarys -> get all the dictionarys.
     */
    @RequestMapping(value = "/dictionarys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Dictionary>> getAllDictionarys(Pageable pageable, @RequestParam(required = false) String filter)
        throws URISyntaxException {
        if ("xref-is-null".equals(filter)) {
            log.debug("REST request to get all Dictionarys where xref is null");
            return new ResponseEntity<>(StreamSupport
                .stream(dictionaryRepository.findAll().spliterator(), false)
                .filter(dictionary -> dictionary.getXref() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        
        Page<Dictionary> page = dictionaryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dictionarys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /dictionarys/:id -> get the "id" dictionary.
     */
    @RequestMapping(value = "/dictionarys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Dictionary> getDictionary(@PathVariable Long id) {
        log.debug("REST request to get Dictionary : {}", id);
        return Optional.ofNullable(dictionaryRepository.findOne(id))
            .map(dictionary -> new ResponseEntity<>(
                dictionary,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /dictionarys/:id -> delete the "id" dictionary.
     */
    @RequestMapping(value = "/dictionarys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDictionary(@PathVariable Long id) {
        log.debug("REST request to delete Dictionary : {}", id);
        dictionaryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("dictionary", id.toString())).build();
    }
}
