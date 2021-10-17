package com.company.ProductManagementSystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.ProductManagementSystem.entity.Product;
import com.company.ProductManagementSystem.services.IProductService;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	/*
	 * http:localhost:8080/product/save
	 */
	
	@PostMapping(value = "/product/save")
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){
		Product saveProduct = productService.savaProduct(product);
		return new ResponseEntity<Product>(saveProduct,HttpStatus.CREATED);//201
	}
	
	@GetMapping(value = "/allProducts")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> allProducts = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
	}
	
	@GetMapping(value = "/{pid}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer pid){
		Product product = productService.getProduct(pid);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{pid}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer pid){
		productService.deleteProduct(pid);
		String msg = "product with " +pid + "deleted successfully";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	
	@PutMapping(value = "/{pid}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer pid, Product product){
		Product updatedProduct = productService.updateProduct(pid, product);
		return new ResponseEntity<Product>(updatedProduct,HttpStatus.CREATED);
	}
	
	@PatchMapping(value="/{pid}/{price}")
	public ResponseEntity<Product> updateProductPrice(@PathVariable Integer pid, @PathVariable Double price){
		Product updateProduct = productService.updateProduceByPrice(pid, price);
		return new ResponseEntity<Product>(updateProduct,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/price/{price}")
	public ResponseEntity<List<Product>> getProductByPrice(@PathVariable Double price){
		List<Product> allProductByPrice = productService.getProductsForPriceGreaterThan(price);
		return new ResponseEntity<List<Product>>(allProductByPrice,HttpStatus.OK);
	}
	
}
