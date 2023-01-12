package com.paulsofts.blogapplicationservices.services;

import java.util.List;

import com.paulsofts.blogapplicationservices.data.Post;
import com.paulsofts.blogapplicationservices.payloads.PostDto;

public interface PostService {
	
	public PostDto createPost(PostDto postDto, int userId, int categoryId);
	public PostDto updatePost(PostDto postDto, int postId);
	public PostDto getPostById(int postId);
	public List<PostDto> getAllPost();
	public void deletePost(int postId);
	public List<PostDto> getAllPostByUser(int userId);
	public List<PostDto> getAllPostByCategory(int categoryId);
	public List<PostDto> searchPost(String keyword);
	

}
