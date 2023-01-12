package com.paulsofts.blogapplicationservices.data;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	@Column(name="postTitle")
	private String postTitle;
	@Column(name="postContent", length=1000)
	private String postContent;
	@Column(name="postImage")
	private String postImage;
	@Column(name="postDate")
	private Date postDate;
	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category category;
	@ManyToOne
	private User user;
	

}
