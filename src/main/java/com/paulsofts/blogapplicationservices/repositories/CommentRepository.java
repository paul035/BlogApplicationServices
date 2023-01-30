package com.paulsofts.blogapplicationservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulsofts.blogapplicationservices.data.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
