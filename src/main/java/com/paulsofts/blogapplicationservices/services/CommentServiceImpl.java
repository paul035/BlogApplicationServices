package com.paulsofts.blogapplicationservices.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulsofts.blogapplicationservices.data.Comment;
import com.paulsofts.blogapplicationservices.data.Post;
import com.paulsofts.blogapplicationservices.data.User;
import com.paulsofts.blogapplicationservices.exceptions.ResourceNotFoundException;
import com.paulsofts.blogapplicationservices.payloads.CommentDto;
import com.paulsofts.blogapplicationservices.repositories.CommentRepository;
import com.paulsofts.blogapplicationservices.repositories.PostRepository;
import com.paulsofts.blogapplicationservices.repositories.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, int postId, int userId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment commentSavedToDB = this.commentRepository.save(comment);
		return this.modelMapper.map(commentSavedToDB, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		
		Comment comment = this.commentRepository.findById(commentId)
		.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		this.commentRepository.delete(comment);
	}

}
