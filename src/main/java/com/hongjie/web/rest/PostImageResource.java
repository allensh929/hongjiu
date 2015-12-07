package com.hongjie.web.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public ResponseEntity<PostImageDTO> createWithSingleImage(
			@RequestParam(value = "productId", required = false) Long productId,
			@RequestParam(value = "file", required = false) MultipartFile file) throws URISyntaxException, IOException {

		log.debug("REST request to save single image for post id = : {}", productId);
		log.debug("path:" + System.getProperty("user.dir"));

		if (productId == 0) {// for creating a new product without a id now
			PostImageDTO postImage = postService.saveSingleImageForPost(file);

			return ResponseEntity.ok(postImage);
		}

		if (productId != null) {
			PostImageDTO postImage = postService.saveSingleImageForPost(productId, file);

			return ResponseEntity.ok(postImage);
		} else {

			return ResponseEntity.badRequest().body(null);
		}

	}
	
	@RequestMapping(value = "/ckuploadImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<PostImageDTO> ckuploadImage(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "CKEditorFuncNum", required = false) String CKEditorFuncNum,
			@RequestParam(value = "uploadContentType", required = false) String uploadContentType,
			@RequestParam(value = "fileName", required = false) String fileName,			
			@RequestParam(value = "uploadFileName", required = false) String uploadFileName,
			@RequestParam(value = "upload", required = false) MultipartFile upload) throws URISyntaxException, IOException {

		log.debug("REST request to save single image for post id = : {}");
		log.debug("path:" + System.getProperty("user.dir"));


		if (upload != null) {
			PostImageDTO postImage = postService.ckuploadImage(upload);
			
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");  
	        out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum  
	                + ",'" + request.getContextPath() + "/assets/images/upload/" + postImage.getImage() + "','')");  
	        out.println("</script>");

			return ResponseEntity.ok(postImage);
		} else {

			return ResponseEntity.badRequest().body(null);
		}

	}

}
