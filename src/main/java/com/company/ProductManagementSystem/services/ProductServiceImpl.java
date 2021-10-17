package com.company.ProductManagementSystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.ProductManagementSystem.entity.Product;
import com.company.ProductManagementSystem.exceptionhandler.ProductNotFoundException;
import com.company.ProductManagementSystem.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product savaProduct(Product product) {
		Product saveProduct = productRepository.save(product);
		return saveProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;
	}

	// @Override
	// public Product getProduct(Integer pid) {
	// Optional<Product> opt = productRepository.findById(pid);
	// if(opt.isPresent()) {
	// Product product = opt.get();
	// return product;
	// }else {
	// throw new ProductNotFoundException("Product with given ID: " +pid + " not
	// Available");
	// }
	//
	// }

	@Override
	public Product getProduct(Integer pid) {
		return productRepository.findById(pid)
				.orElseThrow(() -> new ProductNotFoundException("Product with given id " + pid + " Not Avaliable"));

	}

	@Override
	public void deleteProduct(Integer pid) {
		Product product = getProduct(pid);
		productRepository.deleteById(product.getPid());

	}

	@Override
	public Product updateProduceByPrice(Integer pid, Double newPrice) {
		Product product = getProduct(pid);
		product.setPrice(newPrice);
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Integer pid, Product product) {
		Optional<Product> opt = productRepository.findById(pid);
		if (opt.isPresent()) {
			Product prod = opt.get();
			prod.setPname(product.getPname());
			prod.setPrice(product.getPrice());
			return productRepository.save(prod);
		} else {
			return productRepository.save(product);
		}

	}

	@Override
	public List<Product> getProductsForPriceGreaterThan(Double price) {
		return productRepository.findByPriceGreaterThan(price);
	}
}
