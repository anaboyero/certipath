package com.certipath.application;

import com.certipath.application.exceptions.InvalidEnrollmentException;
import com.certipath.domain.Enrollment;
import com.certipath.domain.Route;
import com.certipath.domain.User;

public class EnrollmentUseCase {

    public EnrollmentUseCase() {
    }

    Enrollment enroll(String userId, String routeId) {
        if (userId == null || routeId == null) {
            throw new InvalidEnrollmentException("User or route does not exist");
        }
        String trimmedUserId = userId.trim();
        String trimmedRouteId = routeId.trim();

        if (trimmedUserId.isEmpty() || trimmedRouteId.isEmpty()) {
            throw new InvalidEnrollmentException("User or route does not exist");
        }

        User user = new User(userId, "Sample User");
        Route route = new Route(routeId, "Sample Route");
        return new Enrollment(user, route);
    }
}