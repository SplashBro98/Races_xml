package com.epam.races.service;

import com.epam.races.entity.User;
import com.epam.races.storage.FakeStorage;

public class LoginService {

    public boolean checkUser(String login, String password){
        User user = new User(login,password);
        return FakeStorage.getInstance().isPresent(user);
    }
}
