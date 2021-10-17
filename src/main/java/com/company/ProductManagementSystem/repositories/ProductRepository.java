package com.company.ProductManagementSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.ProductManagementSystem.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	public List<Product> findByPriceGreaterThan(Double price);
}
