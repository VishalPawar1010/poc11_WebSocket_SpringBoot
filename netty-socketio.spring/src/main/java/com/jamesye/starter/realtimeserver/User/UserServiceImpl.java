package com.jamesye.starter.realtimeserver.User;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    private final Map<Integer, User> userDatabase = new HashMap<>();

    public UserServiceImpl() {
        initializeUserDatabase();
    }

    private void initializeUserDatabase() {
        userDatabase.put(1, new User(222, "John", "male", true));
        userDatabase.put(2, new User(223, "ccccc", "male", true));
        userDatabase.put(3, new User(224, "aaaaa", "male", true));
        userDatabase.put(4, new User(225, "bbbbb", "male", true));
    }

    @Override
    public User getUserInfo(Integer userId,ContextInfo contextInfo) {
        User user = userDatabase.get(userId);
        if (user != null && user.getGender().equals(contextInfo.getGender()) && user.isActive() == contextInfo.isActive()) {
            return user;
        }
        return null;
    }
}