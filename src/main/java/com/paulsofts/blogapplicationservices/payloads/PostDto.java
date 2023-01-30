package com.paulsofts.blogapplicationservices.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paulsofts.blogapplicationservices.data.Category;
import com.paulsofts.blogapplicationservices.data.Comment;
import com.paulsofts.blogapplicationservices.data.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postId;
	private String postTitle;
	private String postContent;
	private String postImage;
	private Date postDate;
	private CategoryDto category;
	private UserDto user;
	private List<CommentDto> comments = new ArrayList<>();

}
