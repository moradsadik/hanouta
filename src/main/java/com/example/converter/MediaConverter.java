package com.example.converter;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;





import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;





import com.example.DemoApplication;
import com.example.domain.Media;

public class MediaConverter implements Converter<List<MultipartFile>, List<Media>>{

	public String path = DemoApplication.UPLOAD_DIR +File.separator ;
	public MediaConverter() {}

	@Override
	public List<Media> convert(List<MultipartFile> files) {
		 
		return Stream.of(files)
		      .map(t-> { 
		    	  MultipartFile f = (MultipartFile) t;
		    	  byte[] bit = null;
					try { bit = f.getBytes();
					} catch (Exception e) { e.printStackTrace(); }
			      return new Media(bit, path+f.getOriginalFilename());
				})
			 .collect(Collectors.toList());
	}

}
