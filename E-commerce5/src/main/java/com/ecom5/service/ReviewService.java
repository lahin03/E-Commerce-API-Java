package com.ecom5.service;

import java.util.List;

import com.ecom5.exception.ProductException;
import com.ecom5.model.Review;
import com.ecom5.model.User;
import com.ecom5.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user)throws ProductException;
	
	public List<Review>getAllReview(Long productId);
}


