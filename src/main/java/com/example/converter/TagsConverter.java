package com.example.converter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.example.domain.Tag;
import com.example.repository.TagRepository;




public class TagsConverter  implements Converter<String, Set<Tag>>{
	
	@Autowired
	TagRepository tr;
	
	public TagsConverter() {}
	
	@Override
	public Set<Tag> convert(String listTags) {
		Set<Tag> tags = null;
		String[] ts = listTags.split(",");
		tags = Stream.of(ts)
		      .map(t -> { return new Tag(t); })
		      .collect(Collectors.toSet());
		
		return tags;
	}

}
