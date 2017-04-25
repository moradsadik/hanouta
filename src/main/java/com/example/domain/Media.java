package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(callSuper=true, exclude={"product"})
public class Media{
	@Getter @Setter @Id @GeneratedValue private Long Id;
	@JsonIgnore @Getter @Setter @Lob private byte[] file;
	@Getter @Setter private String path;
	@JsonIgnore @Getter @Setter @ManyToOne @JoinColumn(name="product_id") Product product;
	
	public Media(){}

	public Media(byte[] file, String path) {
		super();
		this.file = file;
		this.path = path;
	}
	
}