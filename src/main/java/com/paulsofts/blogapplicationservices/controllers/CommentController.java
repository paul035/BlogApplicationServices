package com.paulsofts.blogapplicationservices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulsofts.blogapplicationservices.payloads.CommentDto;
import com.paulsofts.blogapplicationservices.services.CommentServiceImpl;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	@PostMapping("/create/{postId}/{userId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") int postId, 
			@PathVariable("userId") int userId){
		CommentDto createdCommentDto = this.commentServiceImpl.createComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(createdCommentDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentId") int commentId){
		this.commentServiceImpl.deleteComment(commentId);
		return new ResponseEntity<String>("Comment deleted", HttpStatus.OK);
	}

}
