package com.paulsofts.blogapplicationservices.payloads;

import com.paulsofts.blogapplicationservices.data.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
	
	private int commentId;
	private String commentMessage;
	

}
