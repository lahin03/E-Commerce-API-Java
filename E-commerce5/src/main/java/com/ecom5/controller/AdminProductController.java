package com.ecom5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom5.exception.ProductException;
import com.ecom5.model.Product;
import com.ecom5.request.CreateProductRequest;
import com.ecom5.response.ApiResponse;
import com.ecom5.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/")
	public ResponseEntity<Product>createProduct(@RequestBody CreateProductRequest req){
		
		Product product = productService.createProduct(req);
		
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse>deleteProduct(@PathVariable Long ProductId)
			throws ProductException{
		
		productService.deleteProduct(ProductId);   
		
		ApiResponse res = new ApiResponse();
		res.setMessage("product deleted successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	//function to get all products
	@GetMapping("/all")
    public ResponseEntity<Page<Product>> findAllProduct( 
            @RequestParam(required = false) String category,
            @RequestParam(required = false) List<String> colors,
            @RequestParam(required = false) List<String> size,
            @RequestParam(required = false, defaultValue = "0") Integer minPrice,
            @RequestParam(required = false, defaultValue = "1000000") Integer maxPrice,
            @RequestParam(required = false, defaultValue = "0") Integer minDiscount,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String stock,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Minimum price cannot be greater than maximum price");
        }

        Page<Product> products = productService.getAllProduct(
                category,
                colors,
                size,
                minPrice,
                maxPrice,
                minDiscount,
                sort,
                stock,
                pageNumber,
                pageSize);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

	
	@PutMapping("/{productId}/update")
	public ResponseEntity<Product>updateProduct(@RequestBody Product req,
			@PathVariable Long productId) throws ProductException {
		
		Product product = productService.updateProduct(productId, req);
		
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@PostMapping("/creates")
	public ResponseEntity<ApiResponse>createMultipleProduct
	(@RequestBody CreateProductRequest[] req){
		
		for(CreateProductRequest product:req) {
			productService.createProduct(product);
		}
		
		ApiResponse res =new ApiResponse();
		res.setMessage("product deleted successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	
}
