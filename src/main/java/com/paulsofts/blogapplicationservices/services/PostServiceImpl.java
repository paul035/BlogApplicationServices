package com.paulsofts.blogapplicationservices.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paulsofts.blogapplicationservices.data.Category;
import com.paulsofts.blogapplicationservices.data.Post;
import com.paulsofts.blogapplicationservices.data.User;
import com.paulsofts.blogapplicationservices.exceptions.ResourceNotFoundException;
import com.paulsofts.blogapplicationservices.payloads.PostDto;
import com.paulsofts.blogapplicationservices.repositories.CategoryRepository;
import com.paulsofts.blogapplicationservices.repositories.PostRepository;
import com.paulsofts.blogapplicationservices.repositories.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		
		post.setPostImage("default.png");
		post.setPostDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post postSavedToDB = this.postRepository.save(post);
		
		return this.modelMapper.map(postSavedToDB, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setPostImage(postDto.getPostImage());
		Post updatePost = this.postRepository.save(post);
		return this.modelMapper.map(updatePost, PostDto.class);
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> postList = this.postRepository.findAll();
		List<PostDto> postDtoList = postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
		this.postRepository.delete(post);
	}

	@Override
	public List<PostDto> getAllPostByUser(int userId) {
		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		List<Post> postList = this.postRepository.findByUser(user);
		List<PostDto> postDtoList = postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

	@Override
	public List<PostDto> getAllPostByCategory(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
		List<Post> postList = this.postRepository.findByCategory(category);
		List<PostDto> postDtoList = postList.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtoList;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
