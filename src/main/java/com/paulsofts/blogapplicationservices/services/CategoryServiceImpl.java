package com.paulsofts.blogapplicationservices.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paulsofts.blogapplicationservices.data.Category;
import com.paulsofts.blogapplicationservices.exceptions.ResourceNotFoundException;
import com.paulsofts.blogapplicationservices.payloads.CategoryDto;
import com.paulsofts.blogapplicationservices.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category categorySavedToDB = this.categoryRepository.save(this.dtoToCategory(categoryDto));
		return this.categoryToDto(categorySavedToDB);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		//older category - saved to database
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		
		//update category(from DB) to new category(categoryDto)
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedCategory = this.categoryRepository.save(category);
		return this.categoryToDto(updatedCategory);
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		return this.categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categoryList = this.categoryRepository.findAll();
		List<CategoryDto> categoryDtoList = 
				//map() in stream, return a stream consisting of the result after applying given function to the elements of steam
				categoryList.stream().map(category -> this.categoryToDto(category)).collect(Collectors.toList());
		return categoryDtoList;
	}

	@Override
	public void DeleteCategory(int categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
		this.categoryRepository.delete(category);
		
	}
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	public CategoryDto categoryToDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
