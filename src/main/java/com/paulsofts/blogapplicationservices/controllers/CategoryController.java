package com.paulsofts.blogapplicationservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paulsofts.blogapplicationservices.payloads.CategoryDto;
import com.paulsofts.blogapplicationservices.services.CategoryServiceImpl;
import com.paulsofts.blogapplicationservices.utils.GenericResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<GenericResponse<CategoryDto>> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createdCategoryDto = this.categoryServiceImpl.createCategory(categoryDto);
		return new ResponseEntity<GenericResponse<CategoryDto>>(new GenericResponse<CategoryDto>(createdCategoryDto, "category created", "Successful"), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<GenericResponse<CategoryDto>> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("id") int categoryId){
		CategoryDto updatedCategoryDto = this.categoryServiceImpl.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<GenericResponse<CategoryDto>>(new GenericResponse<CategoryDto>(updatedCategoryDto, "category updated", "Successful"), HttpStatus.OK);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<GenericResponse<CategoryDto>> getCategoryById(@PathVariable("id") int categoryId){
		CategoryDto categoryDto = this.categoryServiceImpl.getCategoryById(categoryId);
		return new ResponseEntity<GenericResponse<CategoryDto>>(new GenericResponse<CategoryDto>(categoryDto, "category", "OK"), HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<GenericResponse<List<CategoryDto>>> getAllCategory(){
		List<CategoryDto> categoryDtoList = this.categoryServiceImpl.getAllCategory();
		return new ResponseEntity<GenericResponse<List<CategoryDto>>>(new GenericResponse<List<CategoryDto>>(categoryDtoList, "cateogy list", "OK"), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GenericResponse<String>> deleteCategory(@PathVariable("id") int categoryId){
		this.categoryServiceImpl.DeleteCategory(categoryId);
		return new ResponseEntity<GenericResponse<String>>(new GenericResponse<String>("categoryId: " + categoryId, "category deleted", "Successful"), HttpStatus.OK);
		
	}

}
