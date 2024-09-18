package com.product.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.product.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	public Product findProductByProductName(String productName);
}

