//package com.certipath.application;
//
//import com.certipath.domain.Enrollment;
//import com.certipath.domain.Route;
//import com.certipath.domain.User;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//public class EnrollmentServiceTest {
//
//    @Autowired
//    EnrollmentService enrollmentService;
//
//    @Test
//    void callEnrollmentServiceTest() {
//        // Given a valid User and Route
//        User user = new User("user-id");
//        Route route = new Route("route-id");
//
//        // When enrolling the User in the Route
//        Enrollment enrollment = enrollmentService.enroll(user, route);
//
//        // Then verify the Enrollment is created correctly
//        assertThat(enrollment.getUser()).isEqualTo(user);
//        assertThat(enrollment.getRoute()).isEqualTo(route);
//        assertThat(enrollment.getEnrolledAt()).isNotNull();
//    }
//}
