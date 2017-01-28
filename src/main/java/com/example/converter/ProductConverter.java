package com.example.converter;

import java.io.IOException;

import org.springframework.core.convert.converter.Converter;

import com.example.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductConverter implements Converter<String,Product> {
	
	public ProductConverter(){}
	@Override
	public Product convert(String produit) {
		Product product = null;
		try {
			product = new ObjectMapper().readValue(produit, Product.class);
		} catch (IOException e) { e.printStackTrace(); }
		return product;
	}

}
