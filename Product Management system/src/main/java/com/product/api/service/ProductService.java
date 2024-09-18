package com.product.api.service;
import java.util.List;

import com.product.api.entity.Product;
public interface ProductService {

	void deleteProductById(int id);

	Product updateProduct(Product product);

	Product addproducts(Product product);

	List<Product> getALLProducts();

	Product getProducts(int id);

	Product findByProductName(String ProductName);

	

	//Product findProductByProductName(String productName);
	
}
