package com.ecom5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom5.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
