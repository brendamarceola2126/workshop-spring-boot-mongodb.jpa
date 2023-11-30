package com.brendamarceola.workshopmongo.resources;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.brendamarceola.workshopmongo.domain.Post;
import com.brendamarceola.workshopmongo.resources.util.URL;
import com.brendamarceola.workshopmongo.services.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostResouce {
	
	@Autowired
	private PostService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Optional<Post>> findById(@PathVariable String id){
		Optional<Post> obj = service.findById(id);
		return ResponseEntity.ok().body(obj); }
	
	
	@GetMapping(value="/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text){
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = (Date) URL.convertDate(minDate, new Date(0L));
		Date max = (Date) URL.convertDate(maxDate, new Date(0));
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
		
	}
	
	
	}

	

