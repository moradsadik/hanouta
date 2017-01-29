package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	
	public Tag findByName(String name);
	public Tag findByNameContains(String name);
	
}
