package com.ecom5.model;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	private LocalDateTime createdAt;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public Collection<? extends GrantedAuthority> getAuthorities(){
		
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.toString());
		
		return List.of(simpleGrantedAuthority);
	}
	
	@Column(name  = "mobile", nullable = true)
	private String mobile;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Address> address = new ArrayList<>();
	
	@Embedded
	@ElementCollection
	@CollectionTable(name="payment_information",joinColumns=@JoinColumn(name="user_id"))
	private List<PaymentInformation> paymentInformation = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Rating>ratings = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Review>reviews = new ArrayList<>();
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public List<Address> getAddress() {
		return address;
	}



	public void setAddress(List<Address> address) {
		this.address = address;
	}



	public List<PaymentInformation> getPaymentInformation() {
		return paymentInformation;
	}



	public void setPaymentInformation(List<PaymentInformation> paymentInformation) {
		this.paymentInformation = paymentInformation;
	}



	public List<Rating> getRatings() {
		return ratings;
	}



	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}



	public List<Review> getReviews() {
		return reviews;
	}



	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}



	public User(Long id, String firstName, String lastName, String password, String email, Role role, String mobile,
			List<Address> address, List<PaymentInformation> paymentInformation, List<Rating> ratings,
			List<Review> reviews, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.mobile = mobile;
		this.address = address;
		this.paymentInformation = paymentInformation;
		this.ratings = ratings;
		this.reviews = reviews;
		this.createdAt = createdAt;
	}



	public User() {
		
	}
}
