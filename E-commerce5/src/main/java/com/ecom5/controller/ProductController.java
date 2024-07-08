package com.ecom5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom5.model.Product;
import com.ecom5.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<Page<Product>>findProductByCategoryHandler(
			@RequestParam String category,
			@RequestParam List<String>colour, @RequestParam List<String>size,
			@RequestParam Integer minPrice, @RequestParam Integer maxPrice,
			@RequestParam Integer minDiscount, @RequestParam String sort,
			@RequestParam String stock, @RequestParam Integer pageNumber,
			@RequestParam Integer pageSize) {
		
		Page<Product> res = productService.getAllProduct(category, colour, size, minPrice,
				maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
		
		System.out.println("complete products");
		
		return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	}
}
