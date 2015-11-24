package com.hongjie.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PostImageDTO {

	private Long productId;
	private String image;

	@JsonCreator
	public PostImageDTO(Long productId, String image) {
		super();
		this.productId = productId;
		this.image = image;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "ImageDTO{" + "productId='" + productId + '\'' + ", image='" + image + '\'' + '}';
	}
}
