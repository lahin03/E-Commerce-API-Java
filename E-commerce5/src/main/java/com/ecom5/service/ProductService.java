package com.ecom5.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecom5.exception.ProductException;
import com.ecom5.model.Product;
import com.ecom5.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req);
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productiD, Product req) throws ProductException;
	
	public Product findProductById(Long id) throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	Page<Product> getAllProduct(
            String category,
            List<String> colors,
            List<String> size,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber,
            Integer pageSize);
}
