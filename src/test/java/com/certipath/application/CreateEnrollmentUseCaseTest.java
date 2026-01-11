package com.certipath.application;

import com.certipath.application.exceptions.InvalidEnrollmentException;
import com.certipath.domain.Enrollment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CreateEnrollmentUseCaseTest {

    private EnrollmentUseCase enrollmentUseCase;

    @BeforeEach
            void setUp() {
        enrollmentUseCase = new EnrollmentUseCase();
    }

    @Test
    void testValidEnrollment() {
        // Given a valid user ID and route ID
        String VALID_USER_ID = "user-123";
        String VALID_ROUTE_ID = "route-456";

        // When CreateEnrollmentUseCase is executed
        Enrollment enrollment = enrollmentUseCase.enroll(VALID_USER_ID, VALID_ROUTE_ID);

        // Then verify the enrollment is created correctly.
        assertThat(enrollment.getUser().getId()).isEqualTo(VALID_USER_ID);
        assertThat(enrollment.getRoute().getId()).isEqualTo(VALID_ROUTE_ID);
        assertThat(enrollment.getEnrolledAt()).isNotNull();
    }


    @Test
    void testNullIdEnrollment_throwsException() {
        String NON_VALID_USER_ID = null;
        String NON_VALID_ROUTE_ID = null;


        // Then
        assertThatThrownBy(() ->
                enrollmentUseCase.enroll(NON_VALID_USER_ID, NON_VALID_ROUTE_ID)
        )
                .isInstanceOf(InvalidEnrollmentException.class)
                .hasMessage("User or route does not exist");
    }

    @Test
    void testEmptyEnrollment_throwsException() {
        // Given a non valid user ID or route ID
        String NON_VALID_USER_ID = "";
        String NON_VALID_ROUTE_ID = "";


        // Then
        assertThatThrownBy(() ->
                enrollmentUseCase.enroll(NON_VALID_USER_ID, NON_VALID_ROUTE_ID)
        )
                .isInstanceOf(InvalidEnrollmentException.class)
                .hasMessage("User or route does not exist");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n"})
    void testNonValidEnrollment_throwsException(String userId) {
        assertThatThrownBy(() ->
                enrollmentUseCase.enroll(userId, "a route id")
        )
                .isInstanceOf(InvalidEnrollmentException.class)
                .hasMessage("User or route does not exist");
    }


}
