package com.certipath.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;


public class EnrollmentTest {
    @Test
    void should_create_enrollment_with_route_and_user() {
        Route route = new Route("route-id");
        User user = new User("user-id");

        Enrollment enrollment = new Enrollment(user, route);

        assertThat(enrollment.getUser()). isEqualTo(user);
        assertThat(enrollment.getRoute()). isEqualTo(route);
        assertThat(enrollment.getEnrolledAt()). isNotNull();
    }

}
