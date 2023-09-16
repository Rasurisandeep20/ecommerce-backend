package com.dnb.ecommerce.service;

import java.util.Optional;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exceptions.IdNotFoundException;
import com.dnb.ecommerce.exceptions.InvalidNameException;

public interface ProductService {
	public Product createProduct(Product product) throws  IdNotFoundException;
	public Optional<Product> getproductById(String productId);
	public Iterable<Product> getAllProducts();
	public boolean deleteProductByID(String productId) throws IdNotFoundException;
	public boolean checkProductId(String productId);
	public Product updateProduct(Product product,String productId) throws InvalidNameException, IdNotFoundException;
}











