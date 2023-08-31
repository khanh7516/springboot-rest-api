package com.example.springrestapi.dao;

import com.example.springrestapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> findAll();

    Optional<User> findById(int id);

    User save(User user);

    void deleteById(int id);





}
