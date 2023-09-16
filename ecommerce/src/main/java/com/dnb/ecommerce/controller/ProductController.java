package com.dnb.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exceptions.IdNotFoundException;
import com.dnb.ecommerce.exceptions.InvalidNameException;
import com.dnb.ecommerce.payload.request.ProductRequest;
import com.dnb.ecommerce.service.ProductService;
import com.dnb.ecommerce.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/products")

public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	RequestToEntityMapper mapper;

	/*
	 * Retrieve All Products** - Endpoint: GET /api/products - Description: Retrieve
	 * a list of all available products. - Business Validations: - No specific
	 * business validations for retrieving all products.
	 */

//GetMapping("/allProducts") is used to get all products details
	@GetMapping("/Products")
	public ResponseEntity<?> getAllAccounts() throws IdNotFoundException {
		List<Product> list = (List<Product>) productService.getAllProducts();
		if (list.size() > 0)
			return ResponseEntity.ok(list);
		else
			throw new IdNotFoundException("No details");
	}

	/*
	 * Retrieve a Product by ID** - Endpoint: GET /api/products/{productId} -
	 * Description: Retrieve product details by providing its unique ID. - Business
	 * Validations: - Check that the product with the given ID exists.
	 */

	@GetMapping("/products/{productId}") // it should helps us to get the specific details,product Id is known as path
	// variable
	public ResponseEntity<?> getProductId(@PathVariable("productId") String productId) throws IdNotFoundException {
		Optional<Product> optional = productService.getproductById(productId);
		if (optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		} else {

			throw new IdNotFoundException("id not there");
		}
	}

	/*
	 * Delete a Product** - Endpoint: DELETE /api/products/{productId} -
	 * Description: Delete a product by providing its unique ID. - Business
	 * Validations: - Ensure that the product with the given ID exists. - Consider
	 * any additional business rules related to product deletion, such as ensuring
	 * the product is not associated with any open orders.
	 */

	// @DeleteMapping("/{productId}") is used delete product based on the productId
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProductByID(@PathVariable("productId") String productId) throws IdNotFoundException {
		if (productService.checkProductId(productId)) {
			productService.deleteProductByID(productId);
			return ResponseEntity.noContent().build();
		} else {
			throw new IdNotFoundException("id not");
		}
	}

	/*
	 * Update a Product** - Endpoint: PUT /api/products/{productId} - Description:
	 * Update product details based on its unique ID. - Business Validations: -
	 * Ensure that the product with the given ID exists. - Ensure that the updated
	 * product name, if provided, is unique (unless it's the same product being
	 * updated).
	 */

	@PutMapping("/products/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable String productId,
			@Valid @RequestBody ProductRequest productRequest) throws IdNotFoundException, InvalidNameException {
		if (productService.checkProductId(productId)) {
			Product product = mapper.getProductEntityObject(productRequest);
			product.setProductId(productId);
			return ResponseEntity.ok(productService.updateProduct(product, productId));
		} else {
			throw new IdNotFoundException("productId does not exist");
		}
	}
	/*
	 * Create a Product** - Endpoint: POST /api/products - Description: Create a new
	 * product listing. - Business Validations: - Ensure that the product name is
	 * unique. - Validate that the price is non-negative. - Check that the product
	 * description is not empty.
	 */

//@PostMapping("/create")  is used to create product details
	@PostMapping("/create") // @RequestMethod+post method.==>Spring 4.3.x
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest product) {
		Product product1 = mapper.getProductEntityObject(product);
		try {
			Product product2 = productService.createProduct(product1);
			return new ResponseEntity<Product>(product2, HttpStatus.CREATED);
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
