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

    UserEntity savedUser;

    String userId;

    @Autowired JpaUserAdapter jpaUserAdapter;

    @Autowired UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldFindUserById() {
        // Given a user in the database and an adapter with a jpa user repository.
        savedUser = userRepository.save(new UserEntity("Test User"));
        userId = savedUser.getId();

        // When checking if the user exists
        Optional<User> result = jpaUserAdapter.findUserById(userId);

        // Then the user should be found
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getId()).isEqualTo(userId);
    }


}
