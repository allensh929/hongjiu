package com.hongjie.web.rest;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hongjie.service.PostService;
import com.hongjie.web.rest.dto.PostImageDTO;

/**
 * REST controller for managing Product.
 */
@RestController
@RequestMapping("/api")
public class PostImageResource {

    private final Logger log = LoggerFactory.getLogger(PostImageResource.class);

    @Inject 
    private PostService postService;
    /**
	 * POST /userPosts -> Create a new userPost.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/postImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<PostImageDTO> createWithSingleImage(@RequestParam(value = "productId", required = false) Long productId,
			@RequestParam(value = "file", required = false) MultipartFile file) throws URISyntaxException, IOException {

		log.debug("REST request to save single image for post id = : {}", productId);
		
		if (productId != null) {
			PostImageDTO postImage = postService.saveSingleImageForPost(productId, file);
			
			return ResponseEntity.ok(postImage);
		} else {
			return ResponseEntity.badRequest().body(null);
		}

	}

}
