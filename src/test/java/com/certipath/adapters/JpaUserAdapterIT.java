package com.certipath.adapters;

import com.certipath.adapters.entities.UserEntity;
import com.certipath.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(JpaUserAdapter.class)
public class JpaUserAdapterIT {

    @Autowired JpaUserAdapter jpaUserAdapter;

    @Autowired UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldFindUserByIdTest() {
        // Given a user in the jpa user repository.
        UserEntity savedUser = userRepository.save(new UserEntity("Test User"));
        String userId = savedUser.getId();

        // When the adapter checks if the user exists
        Optional<User> result = jpaUserAdapter.findUserById(userId);

        // Then the user should be found
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isEqualTo(userId);
    }

    @Test
    void shouldSaveUserTest() {
        // Given a new user without an ID
        User newUser = new User("New User");

        //When the adapter saves the user
        User savedUser = jpaUserAdapter.saveUser(newUser);

        // Then the user should be saved in the repository with an ID
        assertThat(savedUser.getId()).isNotNull();

    }


}
