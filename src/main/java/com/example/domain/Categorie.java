package com.example.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(callSuper=true,exclude={"products"})
public class Categorie{
	@Getter @Setter @Id @GeneratedValue private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private Integer parent;
	@Getter @Setter @OneToMany(mappedBy="categorie") private Collection<Product> products;
	
	public Categorie(){}

	public Categorie(String name, Integer parent) {
		super();
		this.name = name;
		this.parent = parent;
	}
	
}