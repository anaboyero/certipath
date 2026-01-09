package com.certipath.application;

import com.certipath.domain.Enrollment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateEnrollmentUseCaseTest {

    private EnrollmentUseCase enrollmentUseCase;

    @BeforeEach
            void setUp() {
        enrollmentUseCase = new EnrollmentUseCase();
    }

    @Test
    void testCreateCorrectEnrollment() {
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

}
