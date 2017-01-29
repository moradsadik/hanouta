package com.example.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.example.domain.Categorie;
import com.example.repository.CategorieRepository;

public class CategorieConverter implements Converter<String, Categorie> {

	@Autowired
	private CategorieRepository cr;
	
	public CategorieConverter() {}
	@Override
	public Categorie convert(String id) {
		Categorie categorie =null;
//		Categorie categorie =cr.findOne(Long.parseLong(id));
		if(categorie != null) return categorie;
		else return new Categorie("categorie "+id, 0);
	}

}
