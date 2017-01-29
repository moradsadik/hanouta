package com.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.DemoApplication;
import com.example.domain.Categorie;
import com.example.domain.Media;
import com.example.domain.Product;
import com.example.domain.Tag;
import com.example.repository.MediaRepository;
import com.example.repository.ProductRepository;


@Controller
@RequestMapping("/hanouta")
public class RouterController {
	
	@Autowired
	private MediaRepository mr;
	@Autowired
	private ProductRepository pr;
	
	@GetMapping("/acceuil")
	public String acceuil(){ return "index"; } 
	
	@GetMapping("/login")
	public String login(){ return "login"; } 
	
	@GetMapping("/produit")
	public String produit(){ return "produit"; } 
	
	@GetMapping("/produit/add")
	public String produitForm(Model model){ 
		Long id = (pr.getLastProductId() == null) ?  0 : pr.getLastProductId() ;
		
		model.addAttribute("productId", id+1);
		return "produit/add"; 
	} 

	@GetMapping("/cart")
	public String cart(){ return "cart"; }
	
	@PostMapping("/produit/add") 
	public @ResponseBody ResponseEntity<Product> addProduitMultiple( 
							 Product produit, 
							 @RequestParam("cat") Categorie categorie,
							 @RequestParam("cles") Set<Tag> cles,
							 @RequestPart("files") List<MultipartFile> files) {
		
		produit.setCategorie(categorie);
		produit.getTags().addAll(cles);
		//pr.save(produit);
		
		System.out.println("produit : " + produit);
		System.out.println("cles : "    + cles);
		System.out.println("categorie : "    + categorie);
		System.out.println("files : "    + files.size());
		
		return new ResponseEntity<Product>(produit, HttpStatus.OK);
	}
	
	@PostMapping("/media/add/single") 
	public @ResponseBody ResponseEntity<Media> addProduitSingle( 
							@RequestParam("productId") Long productId,
							@RequestPart("file") MultipartFile file) throws Exception {
		 
         String path = DemoApplication.UPLOAD_DIR + "/" + file.getOriginalFilename();
		 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
         FileCopyUtils.copy(file.getInputStream(), bos);
         bos.close();
         
         Product produit =new Product("","",0.0, "","");
         produit.setId(productId);
         pr.save(produit);
         
         Media media = new Media(file.getBytes(), path);
         media.setProduct(produit);
         mr.save(media);
         
         //produit.getPhotos().add(media);
         
		
		return new ResponseEntity<Media>(media, HttpStatus.OK);
	}
	
	@RequestMapping(value="media/{id}", method = RequestMethod.GET )
	public byte[] getImage(@PathVariable Long id) throws Exception{
	     Media media = mr.findOne(id);
	     File file = new File(media.getPath());
	     InputStream in = new FileInputStream(file);
	     return IOUtils.toByteArray(in);
	 }
	    
	
	
}
