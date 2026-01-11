//package com.certipath.application;
//
//import com.certipath.domain.Enrollment;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//@SpringBootTest
//public class CreateEnrollmentUseCaseIT {
//
//    @Autowired
//    EnrollmentUseCase enrollmentUseCase;
//
//    @Test
//    void testValidEnrollment_returnsValuesFromDatabase() {
//
//        // Given a valid user ID and route ID from the database.
//        String VALID_USER_ID = "user-123";
//        String VALID_ROUTE_ID = "route-456";
//
//        EntityRoute routeFromDb = new EntityRoute(VALID_ROUTE_ID, "Database Route");
//        EntityUser userFromDb = new EntityUser(VALID_USER_ID, "Database User");
//
//
//
//        // When trying to create an enrollment in the system
//        Enrollment enrollment = enrollmentUseCase.enroll(VALID_USER_ID, VALID_ROUTE_ID);
//
//        // Then it creates a new enrollment with the mapped user and a route from the database
//        assertThat(enrollment.getUser().getId()).isEqualTo()
//
//    }
//
//}
