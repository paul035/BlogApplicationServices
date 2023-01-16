package com.paulsofts.blogapplicationservices.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDto> post;
	private int pageNum;
	private int pageSize;
	private long totalRecord;
	private int totalPage;
	private boolean lastPage = false;
	

}
