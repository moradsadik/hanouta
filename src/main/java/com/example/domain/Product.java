package com.example.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@ToString(callSuper=true, exclude={"photos", "tags", "categorie", "date"})
public class Product implements Serializable{
	
	@Getter @Setter @Id @GeneratedValue private Long id;
	@NotEmpty @Getter @Setter private String name;
	@NotEmpty @Getter @Setter private String description;
	@NotNull @Getter @Setter private Double prix;
	@NotEmpty @Getter @Setter private String size;
	@NotEmpty @Getter @Setter private String color;
	@Temporal(TemporalType.TIMESTAMP) Date date;
	
	@Getter @Setter @ManyToOne @JoinColumn(name="categorie_id") private Categorie categorie;
	@Getter @Setter @OneToMany(mappedBy="product") private List<Media> photos = new ArrayList<>();
	@Getter @Setter @OneToMany(mappedBy="product") private Set<Tag> tags = new HashSet<>();
	
	public Product(){}
	public Product(String name,String description, Double prix, String size, String color) {
		super();
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.size = size;
		this.color = color;
		this.date = new Date();
	}
	
}

