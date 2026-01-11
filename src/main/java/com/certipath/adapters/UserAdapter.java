package com.certipath.adapters;

import com.certipath.application.UserPort;
import com.certipath.domain.User;

import java.util.Optional;

public class UserAdapter implements UserPort {

    private final UserRepository userRepository;

    public UserAdapter(UserRepository userRepositoryJpa) {
        this.userRepository = userRepositoryJpa;
    }

    @Override
    public boolean userExists(String userId) {
        return userRepository.findById(userId).isPresent();
    }

    @Override
    public void saveUser(User user) {
        // de momento nada
    }

    @Override
    public Optional<User> findUserById(String userId) {
        return Optional.empty();
    }

}
