package com.example;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.converter.ProductConverter;
import com.example.domain.Product;
import com.example.repository.ProductRepository;

@SpringBootApplication
public class DemoApplication extends WebMvcConfigurerAdapter {

	@Autowired
	private ProductRepository pr;
	
	public final static String UPLOAD_DIR="HANOUTA_UPLOADS";
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner demo(ServletContext ctx){
		return args->{ 
			//pr.save(new Product("a", "b", "c", 12.03, "d", "e"));
			new File(UPLOAD_DIR).mkdirs();
		};
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
			registry.addConverter(new ProductConverter());
	}
	
	
}















