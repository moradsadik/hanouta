package com.example;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.converter.CategorieConverter;
import com.example.converter.MediaConverter;
import com.example.converter.ProductConverter;
import com.example.converter.TagsConverter;
import com.example.repository.ProductRepository;

@SpringBootApplication
@Configuration
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
			new File(UPLOAD_DIR).mkdirs();
		};
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
			registry.addConverter(new ProductConverter());
			registry.addConverter(new TagsConverter());
			registry.addConverter(new CategorieConverter());
			registry.addConverter(new MediaConverter());
	}
	
	@Bean
	public MessageSource messageSource() {
	     ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	     messageSource.setBasename("messages");
	     return messageSource;
	}
	
	 @Bean
	  public EmbeddedServletContainerFactory servletContainer() throws IOException {
	    TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
	    tomcat.addAdditionalTomcatConnectors(initiateHttpsConnector());
	    return tomcat;
	  }
	  
	  private Connector initiateHttpsConnector() throws IOException {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();

	    connector.setScheme("https");
	    connector.setPort(8081);
	    connector.setSecure(true);
	    protocol.setSSLEnabled(true);
	    protocol.setKeystoreFile("C:/Users/user/Desktop/data/sprinboot/demo/keystore.p12");
        protocol.setKeystorePass("tomcat");
        protocol.setKeyAlias("tomcat");
        protocol.setKeystoreType("PKCS12");
	    return connector;
	  }
	  
	
}















