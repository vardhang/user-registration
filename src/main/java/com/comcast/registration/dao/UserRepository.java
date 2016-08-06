package com.comcast.registration.dao;

import com.comcast.registration.domain.User;

import java.util.List;

/**
 * Created by vrgude on 7/13/2016.
 */
public interface UserRepository {
    User findById(String id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    List<User> findAllUsers();

    boolean isUserExist(User user);
}
