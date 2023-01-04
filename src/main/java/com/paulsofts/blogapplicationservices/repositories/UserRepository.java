package com.paulsofts.blogapplicationservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paulsofts.blogapplicationservices.data.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
