package com.example.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity 
@ToString(callSuper=true,exclude={})
public class User{
	@Id @GeneratedValue private Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String prenom;
	@Getter @Setter private String email;
	@Getter @Setter private String password;
	@Getter @Setter private String adresse;
	
	public User(){}
	public User(String name, String prenom, String email, String adresse) {
		super();
		this.name = name;
		this.prenom = prenom;
		this.email = email;
		this.adresse = adresse;
	}
	
}
