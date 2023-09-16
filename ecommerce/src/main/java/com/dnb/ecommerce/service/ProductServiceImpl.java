package com.dnb.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.ecommerce.dto.Product;
import com.dnb.ecommerce.exceptions.IdNotFoundException;
import com.dnb.ecommerce.exceptions.InvalidNameException;
import com.dnb.ecommerce.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getproductById(String productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId);
	}

	@Override
	public Iterable<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public boolean deleteProductByID(String productId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		boolean isPresent = productRepository.existsById(productId);
		if (isPresent) {
			productRepository.deleteById(productId);
		} else {
			throw new IdNotFoundException("Id not Found");
		}
		if (productRepository.existsById(productId))
			return false;
		else
			return true;
	}

	@Override
	public boolean checkProductId(String productId) {
		// TODO Auto-generated method stub
		if (productRepository.existsById(productId))
			return true;
		else
			return false;

	}

	@Override
	public Product updateProduct(Product product, String productId) throws InvalidNameException, IdNotFoundException {
		// TODO Auto-generated method stub
		if (productRepository.existsById(productId)) {
			Optional<Product> temporary = productRepository.findById(productId);
			String uName = temporary.get().getProductName();

			if (uName.equals(product.getProductName())) {
				return productRepository.save(product);
			} else {
				if (productRepository.findByProductName(product.getProductName()).isEmpty()) {
					return productRepository.save(product);
				}

				else {
					throw new InvalidNameException(" product name already present");
				}
			}
		}

		else {
			throw new IdNotFoundException("ProductId not found");
		}
	}
}
