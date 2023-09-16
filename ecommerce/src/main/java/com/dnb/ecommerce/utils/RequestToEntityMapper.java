package com.dnb.ecommerce.utils;

import org.springframework.stereotype.Component;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.payload.request.ProductRequest;

@Component
public class RequestToEntityMapper {
	public Product getProductEntityObject(ProductRequest productRequest) {
		Product product = new Product();
		product.setProductName(productRequest.getProductName());
		product.setPrice(productRequest.getPrice());
		product.setProductDescription(productRequest.getProductCategory());
		product.setProductExpiryDate(productRequest.getProductExpiryDate());
		product.setProductDescription(productRequest.getProductDescription());
		return product;
	}
}
