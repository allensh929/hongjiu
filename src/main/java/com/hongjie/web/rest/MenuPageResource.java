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
import com.hongjie.domain.MenuPage;
import com.hongjie.repository.MenuPageRepository;
import com.hongjie.web.rest.util.HeaderUtil;
import com.hongjie.web.rest.util.PaginationUtil;

/**
 * REST controller for managing MenuPage.
 */
@RestController
@RequestMapping("/api")
public class MenuPageResource {

    private final Logger log = LoggerFactory.getLogger(MenuPageResource.class);

    @Inject
    private MenuPageRepository menuPageRepository;

    /**
     * POST  /menuPages -> Create a new menuPage.
     */
    @RequestMapping(value = "/menuPages",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MenuPage> createMenuPage(@RequestBody MenuPage menuPage) throws URISyntaxException {
        log.debug("REST request to save MenuPage : {}", menuPage);
        if (menuPage.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new menuPage cannot already have an ID").body(null);
        }
        MenuPage result = menuPageRepository.save(menuPage);
        return ResponseEntity.created(new URI("/api/menuPages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("menuPage", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /menuPages -> Updates an existing menuPage.
     */
    @RequestMapping(value = "/menuPages",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MenuPage> updateMenuPage(@RequestBody MenuPage menuPage) throws URISyntaxException {
        log.debug("REST request to update MenuPage : {}", menuPage);
        if (menuPage.getId() == null) {
            return createMenuPage(menuPage);
        }
        MenuPage result = menuPageRepository.save(menuPage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("menuPage", menuPage.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menuPages -> get all the menuPages.
     */
    @RequestMapping(value = "/menuPages",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MenuPage>> getAllMenuPages(Pageable pageable)
        throws URISyntaxException {
        Page<MenuPage> page = menuPageRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/menuPages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /menuPages/:id -> get the "id" menuPage.
     */
    @RequestMapping(value = "/menuPages/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MenuPage> getMenuPage(@PathVariable Long id) {
        log.debug("REST request to get MenuPage : {}", id);
        return Optional.ofNullable(menuPageRepository.findOne(id))
            .map(menuPage -> new ResponseEntity<>(
                menuPage,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /menuPages/:id -> delete the "id" menuPage.
     */
    @RequestMapping(value = "/menuPages/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMenuPage(@PathVariable Long id) {
        log.debug("REST request to delete MenuPage : {}", id);
        menuPageRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("menuPage", id.toString())).build();
    }
    
    /**
     * GET  /menuPages/active -> get all the active menuPages.
     */
    @RequestMapping(value = "/menuPages/active",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MenuPage>> getAllFavoProducts()
        throws URISyntaxException {
        List<MenuPage> news = menuPageRepository.findAllActiveMenuPages();
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
