package com.example.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(callSuper=true, exclude={"photos", "tags", "categorie", "date"})
public class Product implements Serializable{
	@Getter @Setter @Id @GeneratedValue private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String titre;
	@Getter @Setter private String description;
	@Getter @Setter private Double prix;
	@Getter @Setter private String size;
	@Getter @Setter private String color;
	
	@Temporal(TemporalType.TIMESTAMP) Date date;
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY) @Getter @Setter private List<Media> photos = new ArrayList<>();;
	@ManyToOne @JoinColumn(name="categorie_id") @Getter @Setter private Categorie categorie;
	@OneToMany(mappedBy="product", fetch=FetchType.LAZY) @Getter @Setter private Set<Tag> tags = new HashSet<>();;
	
	public Product(){}
	public Product(String name,String titre, String description, Double prix, String size, String color) {
		super();
		this.name = name;
		this.titre = titre;
		this.description = description;
		this.prix = prix;
		this.size = size;
		this.color = color;
	}
	
}
