package com.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.example.repository.CategorieRepository;
import com.example.repository.MediaRepository;
import com.example.repository.ProductRepository;
import com.example.repository.TagRepository;


@Controller
@RequestMapping("/hanouta")
public class RouterController {
	
	@Autowired 	private MediaRepository mr;
	@Autowired 	private ProductRepository pr;
	@Autowired	private CategorieRepository cr;
	@Autowired 	private TagRepository tr;
	
	@GetMapping("/acceuil")
	public String acceuil(){ return "index"; } 
	
	@GetMapping("/login")
	public String login(){ return "login"; } 
	
	@GetMapping("/produit")
	public String produit(){ return "produit"; } 
	
	@GetMapping("/produits") public @ResponseBody List<Product> produits(){ return pr.findAll(); } 
	@GetMapping("/medias") public @ResponseBody List<Media> medias(){ return mr.findAll(); } 
	
	@GetMapping("/produit/{id}")
	public @ResponseBody Product produitId(@PathVariable Long id){ return pr.findOne(id); } 
	
	@GetMapping("/produit/add")
	public String produitForm(@ModelAttribute("produit") Product produit){ 
		return "produit/add"; 
	} 

	@GetMapping("/cart")
	public String cart(){ return "cart"; }
	
	@PostMapping("/produit/add") 
	public @ResponseBody ResponseEntity<Product> addProduitMultiple( 
							 @Valid @ModelAttribute("produit") Product produit, 
							 BindingResult error,
							 @RequestParam("categorie") Categorie categorie,
							 @RequestParam("tags") Set<Tag> tags,
							 @RequestPart("files") List<MultipartFile> medias
							 ) throws Exception {
				
		
		//if(medias.size() <2) error.rejectValue("photos","empty.produit.photos");
		if(error.hasErrors()){
			error.getAllErrors().forEach(System.out::println);
			System.out.println("produit : " + produit);
			
		}
		
		
		cr.save(categorie);
		produit.setCategorie(categorie);
		
		
		List<Media> listMedia = new ArrayList<>();
		for (MultipartFile f : medias) {
			
			 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(DemoApplication.UPLOAD_DIR+File.separator+f.getOriginalFilename())));
             FileCopyUtils.copy(f.getInputStream(), bos);
             bos.close();
             Media m = new Media(f.getBytes(), DemoApplication.UPLOAD_DIR+File.separator+f.getOriginalFilename());
			 mr.save(m);
			 listMedia.add(m);
		}
		produit.getPhotos().addAll(listMedia);		
		
		pr.save(produit);
		
		Set<Tag> ts = new HashSet<>();
		for (Tag t : tags) {
			tr.save(t);
			t.setProduct(produit);
			ts.add(t);
		}
		produit.getTags().addAll(ts);
		
		
		System.out.println("produit : " + produit);
		System.out.println("cles : "    + tags);
		System.out.println("categorie : "    + categorie);
		System.out.println("files : "+medias.size()  );
		medias.forEach(f-> System.out.println("file : "+f.getOriginalFilename()));
		
		
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
