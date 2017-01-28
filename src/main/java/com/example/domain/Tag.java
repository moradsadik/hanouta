package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(callSuper=true, exclude={"product"})
public class Tag{
	@Getter @Setter  @Id @GeneratedValue private Long id;
	@Getter @Setter  private String name;
	@Getter @Setter  @ManyToOne @JoinColumn(name="product_id") private Product product;
	
	public Tag(){}
	public Tag(String name, Product product) {
		super();
		this.name = name;
		this.product = product;
	}
	
	
	
}