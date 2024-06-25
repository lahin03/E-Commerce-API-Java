package com.ecom5.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",nullable = false)
	private User user;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id",nullable = false)
	private Product product;
	
	@Column(name="rating")
	private Double rating;
	
	private LocalDateTime createdAt;
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product prouct) {
		this.product = prouct;
	}



	public Double getRating() {
		return rating;
	}



	public void setRating(Double rating) {
		this.rating = rating;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	

	public Rating(long id, User user, Product prouct, Double rating, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.user = user;
		this.product = prouct;
		this.rating = rating;
		this.createdAt = createdAt;
	}



	public Rating() {
		
	}
}
