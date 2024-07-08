package com.ecom5.service;

import java.util.List;


import com.ecom5.exception.ProductException;
import com.ecom5.model.Rating;
import com.ecom5.model.User;
import com.ecom5.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req, User user) throws ProductException;
	
	public List<Rating>getProductsRating(Long productId);
}
