package com.certipath.application;

import com.certipath.domain.User;

import java.util.Optional;

public interface UserPort {
    Optional<User> findUserById(String userId);
    boolean userExists(String userId);
    void saveUser(User user);
}
