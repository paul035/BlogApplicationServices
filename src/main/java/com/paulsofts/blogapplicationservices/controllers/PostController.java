package com.paulsofts.blogapplicationservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paulsofts.blogapplicationservices.payloads.PostDto;
import com.paulsofts.blogapplicationservices.payloads.PostResponse;
import com.paulsofts.blogapplicationservices.services.PostServiceImpl;
import com.paulsofts.blogapplicationservices.utils.AppConstants;

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
	public ResponseEntity<PostResponse> getAllPostByUser(
			@RequestParam(value = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNum,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir,
			@PathVariable("userId") int userId){
		PostResponse postResponse = this.postServiceImpl.getAllPostByUser(pageNum, pageSize, userId, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	@GetMapping("/get/category/{categoryId}")
	public ResponseEntity<PostResponse> getAllPostByCategory(
			@RequestParam(value = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNum,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir,
			@PathVariable("categoryId") int categoryId){
		PostResponse postResponse = this.postServiceImpl.getAllPostByCategory(pageNum, pageSize, categoryId, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	@GetMapping("/get/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") int postId){
		PostDto postDto = this.postServiceImpl.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNum", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNum,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
		PostResponse postResponse = this.postServiceImpl.getAllPost(pageNum, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable("postId") int postId){
		this.postServiceImpl.deletePost(postId);
		return new ResponseEntity<String>("post deleted", HttpStatus.OK);
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword){
		List<PostDto> postDtoList = this.postServiceImpl.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
	}

}
