package com.certipath.application;

import com.certipath.application.exceptions.InvalidEnrollmentException;
import com.certipath.domain.Enrollment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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


    // Throws an exception when attempting to create an enrollment with empty userId or routeId
    @ParameterizedTest
    @CsvSource(
            value = {"'', route-1",
            "'   ', route-1",
            "'\t', route-1",
            "'\n', route-1",
            "user-1, ''",
            "user-1, '    '",
            "user-1, '\t'",
            "user-1, '\n'"})
    void testNonValidEmptyIdEnrollment_throwsException(String userId, String routeId) {
        assertThatThrownBy(() ->
                enrollmentUseCase.enroll(userId, routeId)
        )
                .isInstanceOf(InvalidEnrollmentException.class)
                .hasMessage("User or route ID es empty");
    }

    // Throws an exception when attempting to create an enrollment with null userId or routeId
    @ParameterizedTest
    @CsvSource(
            value = {"null, route-1",
                    "user-1, null"},
            nullValues = "null")
    void testNonValidNullIdEnrollment_throwsException(String userId, String routeId) {
        assertThatThrownBy(() ->
                enrollmentUseCase.enroll(userId, routeId)
        )
                .isInstanceOf(InvalidEnrollmentException.class)
                .hasMessage("User or route ID is null");
    }

}
