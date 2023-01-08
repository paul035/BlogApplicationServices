package com.paulsofts.blogapplicationservices.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	
	private int categoryId;
	@NotEmpty
	@Size(min = 4, message = "must contain atleast 4 characters")
	private String categoryTitle;
	@NotEmpty
	private String categoryDescription;

}
