package com.product.api.controller;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.product.api.entity.Product;
import com.product.api.repository.ProductRepository;
import com.product.api.service.ProductService;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	@Autowired
	ProductRepository repo;
	//localhost:8060/products
	//display the products
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getALLProducts(){
		List<Product> products=repo.findAll();
		if(products.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
		}
		else
		{
			return ResponseEntity.ok(products);
		}	
	}
	@GetMapping("/products/{id}")
	public ResponseEntity<String> getProducts(@PathVariable int id)
	{
	try
	{
		Product product=repo.findById(id).get();
		return ResponseEntity.status(HttpStatus.CREATED).body("Product Displayed Successfully");
		//return ResponseEntity.ok(product);
		//return service.getProducts(id);
	}
	catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product Id is not present");
		}

	}
	//@GetMapping("/products/{id}")
	//public Product getProducts(@PathVariable int id)
	//{
		//Product product=repo.findById(id).get();
		//return service.getProducts(id);
	//}

	//add the products
	//@ResponseStatus(code=HttpStatus.CREATED)
	@PostMapping("/product/add")
	public Product addproducts(@RequestBody Product product) throws Exception{
		String name=product.getProductName();
		if(name!=null && !"".equals(name))
		{
			Product pd =service.findByProductName(name);
			if(pd!=null)
			{
				throw new Exception("Product with"+name+" is already exist .Try to add with different product name");
			}
		}
		Product pd=null;
		pd=service.addproducts(product);
		return pd;
		//return service.addproducts(product);
		//return ResponseEntity.status(HttpStatus.CREATED).body(pr);
		//validate if the product already exist and only add non exisiting product
		//search a product by product name and try to search the existing product
	}
	//update the products
	@PutMapping("/product/update/{id}")
	public ResponseEntity<String> updateproducts(@PathVariable int id,@RequestBody Product products) {
		try {
		Product product=repo.findById(id).get();
		product.setProductName(products.getProductName());
		product.setPrice(products.getPrice());
		Product prod=repo.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body("Product updated Successfully");
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product Id is not present");
		}
		
	}
	//try this method with product name
	//delete the products
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<String> removeProduct(@PathVariable int id) {
		try {
			Product product=repo.findById(id).get();
			repo.delete(product);
			return ResponseEntity.ok("Product Deleted Successfully");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Product Id is not present");
		}
	}
}
