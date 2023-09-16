package com.dnb.ecommerce.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dnb.ecommerce.dto.Product;
@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
	List<Product> findByProductName(String productName);
}
