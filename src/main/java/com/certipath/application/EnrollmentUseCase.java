package com.certipath.application;

import com.certipath.application.exceptions.InvalidEnrollmentException;
import com.certipath.domain.Enrollment;
import com.certipath.domain.Route;
import com.certipath.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentUseCase {
    private final UserPort userPort;
    private final RoutePort routePort;

    public EnrollmentUseCase(UserPort userPort, RoutePort routePort) {
        this.userPort = userPort;
        this.routePort = routePort;
    }

    Enrollment enroll(String userId, String routeId) {
        if (userId == null || routeId == null) {
            throw new InvalidEnrollmentException("User or route ID is null");
        }
        String trimmedUserId = userId.trim();
        String trimmedRouteId = routeId.trim();

        if (trimmedUserId.isEmpty() || trimmedRouteId.isEmpty()) {
            throw new InvalidEnrollmentException("User or route ID is empty");
        }

        Optional<User> user = userPort.findUserById(trimmedUserId);
        Optional<Route> route = routePort.findRouteById(trimmedRouteId);

        if (user.isEmpty()) {
            throw new InvalidEnrollmentException("User not found");
        }
        if (route.isEmpty()) {
            throw new InvalidEnrollmentException("Route not found");
        }
        return new Enrollment(user.get(), route.get());
    }
}