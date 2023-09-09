package com.example.springrestapi.repository;

import com.example.springrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findUserByNameContainingIgnoreCase(String name);




}
