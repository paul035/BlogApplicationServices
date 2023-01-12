package com.paulsofts.blogapplicationservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulsofts.blogapplicationservices.payloads.PostDto;
import com.paulsofts.blogapplicationservices.services.PostServiceImpl;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@PostMapping("/user/{userId}/category/{categoryId}/create")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") int userId,
			@PathVariable("categoryId") int categoryId){
		PostDto createdPostDto = this.postServiceImpl.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/update/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") int postId){
		PostDto updatedPostDto = this.postServiceImpl.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
	}
	
	@GetMapping("/get/user/{userId}")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable("userId") int userId){
		List<PostDto> postDtoList = this.postServiceImpl.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/get/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable("categoryId") int categoryId){
		List<PostDto> postDtoList = this.postServiceImpl.getAllPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}
	
	@GetMapping("/get/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") int postId){
		PostDto postDto = this.postServiceImpl.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> postDtoList = this.postServiceImpl.getAllPost();
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable("postId") int postId){
		this.postServiceImpl.deletePost(postId);
		return new ResponseEntity<String>("post deleted", HttpStatus.OK);
	}

}
