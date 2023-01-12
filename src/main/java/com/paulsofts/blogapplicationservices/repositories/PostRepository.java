package com.paulsofts.blogapplicationservices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulsofts.blogapplicationservices.data.Category;
import com.paulsofts.blogapplicationservices.data.Post;
import com.paulsofts.blogapplicationservices.data.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);

}
