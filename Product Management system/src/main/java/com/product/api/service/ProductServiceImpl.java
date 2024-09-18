package com.product.api.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;
 
import com.product.api.entity.Product;
import com.product.api.repository.ProductRepository;
 
 
 
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepo;
	@Override
	public List<Product> getALLProducts(){
		return productRepo.findAll();
	}
	@Override
	public Product getProducts(int id){
		return productRepo.findById(id).get();

	}
	@Override
	public Product findByProductName(String productName){
		return productRepo.findProductByProductName(productName);

	}
	@Override
	public Product addproducts(Product product) {
		productRepo.save(product);
		return product;
	}
	@Override
	public void deleteProductById(int id)
	{
		productRepo.deleteById(id);
	}
	@Override
	public Product updateProduct(Product product) {
		productRepo.save(product);
		return product;
	}
}
