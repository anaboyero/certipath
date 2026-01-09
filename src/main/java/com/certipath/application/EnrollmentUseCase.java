package com.certipath.application;

import com.certipath.application.exceptions.InvalidEnrollmentException;
import com.certipath.domain.Enrollment;
import com.certipath.domain.Route;
import com.certipath.domain.User;

public class EnrollmentUseCase {

    public EnrollmentUseCase() {
    }

    Enrollment enroll(String userId, String routeId) {
        if (userId == null || userId.isEmpty()) {
            throw new InvalidEnrollmentException("User or route not valid");
        }
        if (routeId == null || routeId.isEmpty()) {
            throw new InvalidEnrollmentException("User or route not valid");
        }
        User user = new User(userId, "Sample User");
        Route route = new Route(routeId, "Sample Route");
        return new Enrollment(user, route);
    }
}