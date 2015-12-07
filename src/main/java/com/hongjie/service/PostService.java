package com.hongjie.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hongjie.config.Constants;
import com.hongjie.domain.Product;
import com.hongjie.repository.ProductRepository;
import com.hongjie.web.rest.dto.PostImageDTO;

@Service("PostService")
@Transactional
public class PostService {

	private final Logger log = LoggerFactory.getLogger(PostService.class);

	@Inject
	private ProductRepository productRepository;

	@Autowired
	ServletContext servletContext;
	// public void createNewPost(UserPost userPost, MultipartFile[] images)
	// throws IOException {
	//
	// if (userPost.getId() == null) {
	//
	// userPost.setGreetCount(0);
	// userPost.setCommentsCount(0);
	// }
	//
	// List<PostImage> postImages = new ArrayList<>();
	//
	// if (images != null && images.length > 0) {
	//
	// for (int i = 0; i < images.length; i++) {
	//
	// MultipartFile file = images[i];
	//
	// String imageFileName = System.currentTimeMillis() + "_" +
	// file.getOriginalFilename();
	//
	// FileOutputStream fileOutputStream = new FileOutputStream(new
	// File(Constants.POST_IMAGE_PATH + imageFileName));
	//
	// IOUtils.copy(file.getInputStream(), fileOutputStream);
	//
	// String src = Constants.POST_IMAGE_RESOURCE_PATH + imageFileName;
	//
	// PostImage postImage = new PostImage();
	// postImage.setSrc(src);
	//
	// postImages.add(postImage);
	//
	// postImage.setUserPost(userPost);
	// postImageRepository.save(postImage);
	// }
	// }
	//
	// userPostRepository.save(userPost);
	//
	// }

	public PostImageDTO saveSingleImageForPost(Long productId, MultipartFile file) throws IOException {

		Product product = productRepository.findOne(productId);

		String imageFileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + "_"
				+ file.getOriginalFilename();
		String rootPath = Constants.USER_UPLOADED_FILE_ROOT_PATH;

		String fileName = rootPath;

		log.debug("new saving file folder -> " + fileName);

		File newSavedFile = new File(fileName);

		if (!newSavedFile.exists()) {
			newSavedFile.mkdir();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName + "/" + imageFileName));

		IOUtils.copy(file.getInputStream(), fileOutputStream);

		product.setImage(imageFileName);
		product = productRepository.saveAndFlush(product);
		PostImageDTO image = new PostImageDTO(product.getId(), imageFileName);

		return image;
	}

	public PostImageDTO saveSingleImageForPost(MultipartFile file) throws IOException {

		String imageFileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + "_"
				+ file.getOriginalFilename();
		String rootPath = Constants.USER_UPLOADED_FILE_ROOT_PATH;

		String fileName = rootPath;

		log.debug("new saving file folder -> " + fileName);

		File newSavedFile = new File(fileName);

		if (!newSavedFile.exists()) {
			newSavedFile.mkdir();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName + "/" + imageFileName));

		IOUtils.copy(file.getInputStream(), fileOutputStream);

		PostImageDTO image = new PostImageDTO(null, imageFileName);

		return image;
	}

	public PostImageDTO ckuploadImage(MultipartFile file) throws IOException {

		String imageFileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + "_"
				+ file.getOriginalFilename();
		String rootPath = Constants.USER_UPLOADED_FILE_ROOT_PATH;

		String fileName = rootPath;

		log.debug("new saving file folder -> " + fileName);

		File newSavedFile = new File(fileName);

		if (!newSavedFile.exists()) {
			newSavedFile.mkdir();
		}
		FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName + "/" + imageFileName));

		IOUtils.copy(file.getInputStream(), fileOutputStream);

		PostImageDTO image = new PostImageDTO(null, imageFileName);
		

		return image;
	}
}
