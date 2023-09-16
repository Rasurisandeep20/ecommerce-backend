package com.dnb.ecommerce.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dnb.ecommerce.utils.CustomProductIdGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data   //It is similar like setters and getters
@Entity
public class Product {
	
	/*
	 product fields: productId
	                 productName
	                 price
	                 productDescription
	                 productExpiryDate
	                 productCategory
	 */
	
	/*
	 * validation conditions for all fields:
	 * productName,productDescription,productCategory should not blank price must be
	 * positive value and notblank productExpiryDate not be blank
	 */
		
	// productId must be Generated value
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@GenericGenerator(name = "product_seq", strategy = "com.dnb.ecommerce.utils.CustomProductIdGenerator", parameters = {
			@Parameter(name = CustomProductIdGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = CustomProductIdGenerator.VALUE_PREFIX_PARAMETER, value = "A_"),
			@Parameter(name = CustomProductIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") }

	)
	private String productId;
	@NotBlank(message = "product name should not be blank")
	private String productName;
	@NotBlank(message = "price should not be blank and negative value")
	private int price;
	@NotBlank(message = "productDescription  should not be blank")
	private String productDescription;
	@NotBlank(message = "LocalDate  should not be blank")
	private LocalDate productExpiryDate;
	@NotBlank(message = "productCategory  should not be blank")
	private String productCategory;
}
