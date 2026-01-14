package com.certipath.application;

import com.certipath.adapters.JpaRouteAdapter;
import com.certipath.adapters.JpaUserAdapter;
import com.certipath.application.exceptions.InvalidEnrollmentException;
import com.certipath.domain.Enrollment;
import com.certipath.domain.Route;
import com.certipath.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class CreateEnrollmentUseCaseIT {

    // Levanto la aplicación. Dado que existen unas rutas y usuarios en la base de datos,
    // veo si al intentar crear una inscripción con IDs
    // la aplicación encuentra o no esos datos correctamente.

    @Autowired
    EnrollmentUseCase enrollmentUseCase;

    @Autowired UserPort userPort;
    @Autowired RoutePort routePort;

    @Autowired
    JpaUserAdapter jpaUserAdapter;

    @Autowired
    JpaRouteAdapter jpaRouteAdapter;

    String existingUserId;
    String existingRouteId;

    @BeforeEach
    void setUpDb() {
        // Precargamos datos en la base de datos
        User newUser = new User("Ana");
        User savedUser = jpaUserAdapter.saveUser(newUser);
        Route newRoute = new Route("Python Basics");
        Route savedRoute = jpaRouteAdapter.saveRoute(newRoute);

        existingUserId = savedUser.getId();
        existingRouteId = savedRoute.getId();
    }

    @Test
    void testValidEnrollment_returnsValuesFromDatabase() {

        // Given a valid user ID and route ID from the database (set up)

        // When trying to create an enrollment in the system
        Enrollment enrollment = enrollmentUseCase.enroll(existingUserId, existingRouteId);

        // Then creates a new enrollment with the correct data from the database
        assertThat(enrollment.getUser().getId()).isEqualTo(existingUserId);
        assertThat(enrollment.getUser().getName()).isEqualTo("Ana");
        assertThat(enrollment.getRoute().getId()).isEqualTo(existingRouteId);
        assertThat(enrollment.getRoute().getName()).isEqualTo("Python Basics");
    }

    @Test
    void test_nonValidEnrollment_NotFoundUserDataInDatabase() {

        // Given a valid user ID and route ID from the database (set up)

        // When trying to create an enrollment with non-existing ID user, throws exception

        assertThatThrownBy(() ->
                enrollmentUseCase.enroll("nonUserID", existingRouteId)
        )
                .isInstanceOf(InvalidEnrollmentException.class)
                .hasMessage("User not found");
    }

    @Test
    void test_nonValidEnrollment_NotFoundRouteDataInDatabase() {

        // Given a valid user ID and route ID from the database (set up)

        // When trying to create an enrollment with non-existing ID route, throws exception

        assertThatThrownBy(() ->
                enrollmentUseCase.enroll(existingUserId, "nonExistingRouteID")
        )
                .isInstanceOf(InvalidEnrollmentException.class)
                .hasMessage("Route not found");
    }

}
