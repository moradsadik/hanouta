package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Categorie;

@Repository 
public interface CategorieRepository extends JpaRepository<Categorie, Long>{}
