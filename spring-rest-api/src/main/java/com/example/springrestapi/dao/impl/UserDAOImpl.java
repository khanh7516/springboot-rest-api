package com.example.springrestapi.dao.impl;


import com.example.springrestapi.dao.UserDAO;
import com.example.springrestapi.database.UserDB;
import com.example.springrestapi.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {


    @Override
    public List<User> findAll() {
        return UserDB.userList;
    }

    @Override
    public Optional<User> findById(int id) {
        return UserDB.userList.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }



    @Override
    public User save(User user) {
        user.setId(createId());
        UserDB.userList.add(user);
        return user;
    }

    @Override
    public void deleteById(int id) {
        UserDB.userList.removeIf(user -> user.getId() == id);
    }


    private int createId() {
        return UserDB.userList.stream()
                .mapToInt(User::getId)
                .max()
                .orElseGet(() -> 1);
    }


}
