package com.paulsofts.blogapplicationservices.data;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="commentId")
	private int commentId;
	@Column(name="commentMessage")
	private String commentMessage;
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post; 
	@ManyToOne
	private User user;

}
