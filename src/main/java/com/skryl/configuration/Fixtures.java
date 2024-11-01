package com.skryl.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.skryl.model.fixtures.User;
import com.skryl.model.fixtures.UserList;

import java.io.FileReader;
import java.io.IOException;

public final class Fixtures {

    public void getFixture() {
        Gson gson = new GsonBuilder().create();
        try {
            UserList userList = gson.fromJson(
                    new FileReader("src/test/resources/fixtures/users.json"), UserList.class
            );

            // Accessing the user data
            for (User user : userList.getUsers()) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Password: " + user.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

