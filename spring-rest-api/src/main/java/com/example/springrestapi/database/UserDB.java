package com.example.springrestapi.database;

import com.example.springrestapi.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    public static List<User> userList = new ArrayList<>(
            List.of(
                    new User(1, "John Doe", "john@example.com", "1234567891", "Address 1", "avatar1.jpg", "password1"),
                    new User(2, "Alice Smith", "alice@example.com", "1234567892", "Address 2", "avatar2.jpg", "password2"),
                    new User(3, "Bob Johnson", "bob@example.com", "1234567893", "Address 3", "avatar3.jpg", "password3"),
                    new User(4, "Emily Brown", "emily@example.com", "1234567894", "Address 4", "avatar4.jpg", "password4"),
                    new User(5, "Michael Lee", "michael@example.com", "1234567895", "Address 5", "avatar5.jpg", "password5"),
                    new User(6, "Sophia Davis", "sophia@example.com", "1234567896", "Address 6", "avatar6.jpg", "password6"),
                    new User(7, "James Wilson", "james@example.com", "1234567897", "Address 7", "avatar7.jpg", "password7"),
                    new User(8, "Emma Miller", "emma@example.com", "1234567898", "Address 8", "avatar8.jpg", "password8"),
                    new User(9, "Liam Garcia", "liam@example.com", "1234567899", "Address 9", "avatar9.jpg", "password9"),
                    new User(10, "Olivia Martinez", "olivia@example.com", "1234567900", "Address 10", "avatar10.jpg", "password10"),
                    new User(11, "Noah Clark", "noah@example.com", "1234567901", "Address 11", "avatar11.jpg", "password11"),
                    new User(12, "Ava Hall", "ava@example.com", "1234567902", "Address 12", "avatar12.jpg", "password12"),
                    new User(13, "William Young", "william@example.com", "1234567903", "Address 13", "avatar13.jpg", "password13"),
                    new User(14, "Isabella White", "isabella@example.com", "1234567904", "Address 14", "avatar14.jpg", "password14"),
                    new User(15, "Ethan Brown", "ethan@example.com", "1234567905", "Address 15", "avatar15.jpg", "password15")
    ));
}
