package com.certipath.adapters;

import com.certipath.adapters.entities.UserEntity;
import com.certipath.application.UserPort;
import com.certipath.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserAdapter implements UserPort {

    private final UserRepository userRepository;

    public JpaUserAdapter(UserRepository userRepositoryJpa) {
        this.userRepository = userRepositoryJpa;
    }

    @Override
    public boolean userExists(String userId) {
        return userRepository.findById(userId).isPresent();
    }

    @Override
    public User saveUser(User user) {
        UserEntity userToSave = new UserEntity(user.getName());
        UserEntity savedUserEntity = userRepository.save(userToSave);
        return new User(savedUserEntity.getId(), savedUserEntity.getName());
    }

    @Override
    public Optional<User> findUserById(String userId) {
        Optional<UserEntity> result = userRepository.findById(userId);
        if(result.isEmpty()) {
            return Optional.empty();
        }
        else {
            return Optional.of(new User(result.get().getId(), result.get().getName()));
        }
    }

}
