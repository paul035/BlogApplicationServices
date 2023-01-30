package com.paulsofts.blogapplicationservices.services;

import com.paulsofts.blogapplicationservices.payloads.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto commentDto, int postId, int userId);
	public void deleteComment(int commentId);

}
