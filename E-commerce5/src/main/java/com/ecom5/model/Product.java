package com.ecom5.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "title")
	private String title;
	
	 @Column(name = "description")
	 private String description;
	 
	 @Column(name = "price")
	 private int price; 
	 
	 @Column(name="discount_price")
	 private int discountedPrice;
	 
	 @Column(name = "discount_percent")
	 private int discountPercent;
	 
	 @Column(name = "quantity")
	 private int quantity;
	 
	 @Column(name = "brand")
	 private String brand;
	 
	 @Column(name = "colour")
	 private String colour;
	 
	 @Embedded
	 @ElementCollection
	 @Column(name = "sizes")
	 private Set<Size> sizes = new HashSet<>();
	 
	 @Column(name = "image_url")
	 private String imageUrl;
	 
	 @OneToMany(mappedBy = "product",cascade=CascadeType.ALL,orphanRemoval = true)
	 private List<Rating>ratings = new ArrayList<>();
	 
	 @OneToMany(mappedBy = "product",cascade=CascadeType.ALL,orphanRemoval = true)
	 private List<Review>reviwes = new ArrayList<>();
	 
	 @Column(name = "num_ratings")
	 private int numRatings;
	 
	 @ManyToOne()
	 @JoinColumn(name = "category_id")
	 private Category category;
	 
	 private LocalDateTime createdAt;
	 
	 
	 
	 public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getDiscountedPrice() {
		return discountedPrice;
	}



	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}



	public int getDiscountPercent() {
		return discountPercent;
	}



	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getColour() {
		return colour;
	}



	public void setColour(String colour) {
		this.colour = colour;
	}



	public Set<Size> getSizes() {
		return sizes;
	}



	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public List<Rating> getRatings() {
		return ratings;
	}



	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}



	public List<Review> getReviwes() {
		return reviwes;
	}



	public void setReviwes(List<Review> reviwes) {
		this.reviwes = reviwes;
	}



	public int getNumRatings() {
		return numRatings;
	}



	public void setNumRatings(int numRatings) {
		this.numRatings = numRatings;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public Product(long id, String title, String description, int price, int discountedPrice, int discountPercent,
			int quantity, String brand, String colour, Set<Size> sizes, String imageUrl, List<Rating> ratings,
			List<Review> reviwes, int numRatings, Category category, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.discountPercent = discountPercent;
		this.quantity = quantity;
		this.brand = brand;
		this.colour = colour;
		this.sizes = sizes;
		this.imageUrl = imageUrl;
		this.ratings = ratings;
		this.reviwes = reviwes;
		this.numRatings = numRatings;
		this.category = category;
		this.createdAt = createdAt;
	}



	private Product() {
		 
	 }
}
