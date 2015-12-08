package com.hongjie.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.hongjie.domain.Photo;
import com.hongjie.repository.PhotoRepository;
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
 * REST controller for managing Photo.
 */
@RestController
@RequestMapping("/api")
public class PhotoResource {

    private final Logger log = LoggerFactory.getLogger(PhotoResource.class);

    @Inject
    private PhotoRepository photoRepository;

    /**
     * POST  /photos -> Create a new photo.
     */
    @RequestMapping(value = "/photos",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo) throws URISyntaxException {
        log.debug("REST request to save Photo : {}", photo);
        if (photo.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new photo cannot already have an ID").body(null);
        }
        Photo result = photoRepository.save(photo);
        return ResponseEntity.created(new URI("/api/photos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("photo", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /photos -> Updates an existing photo.
     */
    @RequestMapping(value = "/photos",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Photo> updatePhoto(@RequestBody Photo photo) throws URISyntaxException {
        log.debug("REST request to update Photo : {}", photo);
        if (photo.getId() == null) {
            return createPhoto(photo);
        }
        Photo result = photoRepository.save(photo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("photo", photo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /photos -> get all the photos.
     */
    @RequestMapping(value = "/photos",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Photo>> getAllPhotos(Pageable pageable)
        throws URISyntaxException {
        Page<Photo> page = photoRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/photos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /photos/:id -> get the "id" photo.
     */
    @RequestMapping(value = "/photos/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Photo> getPhoto(@PathVariable Long id) {
        log.debug("REST request to get Photo : {}", id);
        return Optional.ofNullable(photoRepository.findOne(id))
            .map(photo -> new ResponseEntity<>(
                photo,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /photos/:id -> delete the "id" photo.
     */
    @RequestMapping(value = "/photos/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        log.debug("REST request to delete Photo : {}", id);
        photoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("photo", id.toString())).build();
    }
}
