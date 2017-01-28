package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Media;

@Repository 
public interface MediaRepository extends JpaRepository<Media, Long>{}
