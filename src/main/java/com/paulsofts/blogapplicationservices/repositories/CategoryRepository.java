package com.paulsofts.blogapplicationservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulsofts.blogapplicationservices.data.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
