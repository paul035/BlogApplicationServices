package com.paulsofts.blogapplicationservices.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.paulsofts.blogapplicationservices.data.Category;
import com.paulsofts.blogapplicationservices.data.Post;
import com.paulsofts.blogapplicationservices.data.User;
import com.paulsofts.blogapplicationservices.exceptions.ResourceNotFoundException;
import com.paulsofts.blogapplicationservices.payloads.PostDto;
import com.paulsofts.blogapplicationservices.payloads.PostResponse;
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
	public PostResponse getAllPost(int pageNum, int pageSize, String sortBy, String sortDir) {
//		implementing pagination & sorting
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else if(sortDir.equalsIgnoreCase("desc")) {
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
		Page<Post> postPages = this.postRepository.findAll(pageable);
		List<Post> postList = postPages.getContent();
//		List<Post> postList = this.postRepository.findAll();
		List<PostDto> postDtoList = postList.stream()
				.map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setPost(postDtoList);
		postResponse.setPageNum(postPages.getNumber());
		postResponse.setPageSize(postPages.getSize());
		postResponse.setTotalRecord(postPages.getTotalElements());
		postResponse.setTotalPage(postPages.getTotalPages());
		postResponse.setLastPage(postPages.isLast());
		return postResponse;
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
		this.postRepository.delete(post);
	}

	@Override
	public PostResponse getAllPostByUser(int pageNum, int pageSize, int userId) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Post> postPages = this.postRepository.findAll(pageable);
		List<Post> postList =  postPages.getContent();
//		User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
//		List<Post> postList = this.postRepository.findByUser(user);
		List<PostDto> postDtoList = postList.stream().map(post -> this.modelMapper
				.map(post, PostDto.class)).collect(Collectors.toList());
		List<PostDto> postDtoListByUser = postDtoList.stream().
				filter(postDto -> postDto.getUser().getId() == userId).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setPost(postDtoListByUser);
		postResponse.setPageNum(postPages.getNumber());
		postResponse.setPageSize(postPages.getSize());
		postResponse.setTotalRecord(postPages.getTotalElements());
		postResponse.setTotalPage(postPages.getTotalPages());
		postResponse.setLastPage(postPages.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getAllPostByCategory(int pageNum, int pageSize, int categoryId) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Post> postPages = this.postRepository.findAll(pageable);
		List<Post> postList = postPages.getContent();
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
//		List<Post> postList = this.postRepository.findByCategory(category);
		List<PostDto> postDtoList = postList.stream()
				.map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		List<PostDto> postDtoListByCategory = postDtoList.stream()
				.filter(postDto -> postDto.getCategory().getCategoryId() == categoryId).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setPost(postDtoListByCategory);
		postResponse.setPageNum(postPages.getNumber());
		postResponse.setPageSize(postPages.getSize());
		postResponse.setTotalRecord(postPages.getTotalElements());
		postResponse.setTotalPage(postPages.getTotalPages());
		postResponse.setLastPage(postPages.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
