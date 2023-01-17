package com.paulsofts.blogapplicationservices.services;

import java.util.List;

import com.paulsofts.blogapplicationservices.data.Post;
import com.paulsofts.blogapplicationservices.payloads.PostDto;
import com.paulsofts.blogapplicationservices.payloads.PostResponse;

public interface PostService {
	
	public PostDto createPost(PostDto postDto, int userId, int categoryId);
	public PostDto updatePost(PostDto postDto, int postId);
	public PostDto getPostById(int postId);
	public PostResponse getAllPost(int pageNum, int pageSize, String sortBy, String sortDir);
	/* public List<PostDto> getAllPost(int pageNum, int pageSize); */
	public void deletePost(int postId);
	public PostResponse getAllPostByUser(int pageNum, int pageSize, int userId);
	/* public List<PostDto> getAllPostByUser(int userId); */
	public PostResponse getAllPostByCategory(int pageNum, int pageSize, int categoryId);
	/* public List<PostDto> getAllPostByCategory(int categoryId); */
	public List<PostDto> searchPost(String keyword);
	

}
