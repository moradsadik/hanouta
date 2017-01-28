package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.domain.Product;

@Repository 
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(" select max(p.id) from Product p")
	public Long getLastProductId();
	
}
