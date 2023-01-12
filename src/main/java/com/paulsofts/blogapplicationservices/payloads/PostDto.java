package com.paulsofts.blogapplicationservices.payloads;

import java.util.Date;

import com.paulsofts.blogapplicationservices.data.Category;
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

}
