package com.paulsofts.blogapplicationservices.services;

import java.util.List;

import com.paulsofts.blogapplicationservices.payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	public CategoryDto getCategoryById(int categoryId);
	public List<CategoryDto> getAllCategory();
	public void DeleteCategory(int categoryId);

}
