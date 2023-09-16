package com.dnb.ecommerce.payload.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {
//We have to create fields only which are possible to given
	private String productName;
	@NotBlank(message = "price should not be blank")
	private int price;
	@NotBlank(message = "productDescription  should not be blank")
	private String productDescription;
	private LocalDate productExpiryDate;
	@NotBlank(message = "productCategory  should not be blank")
	private String productCategory;
}