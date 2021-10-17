package com.company.ProductManagementSystem.services;

import java.util.List;

import com.company.ProductManagementSystem.entity.Product;

/*contains all the abstract methods*/
public interface IProductService {
	Product savaProduct(Product product);
	List<Product> getAllProducts();
	Product getProduct(Integer pid);
	void deleteProduct(Integer pid);
	Product updateProduct(Integer pid, Product product);
	public List<Product> getProductsForPriceGreaterThan(Double price);
	Product updateProduceByPrice(Integer pid, Double newPrice);
}
