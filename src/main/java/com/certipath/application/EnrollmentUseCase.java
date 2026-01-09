package com.certipath.application;

import com.certipath.domain.Enrollment;
import com.certipath.domain.Route;
import com.certipath.domain.User;
import org.springframework.stereotype.Service;

public class EnrollmentUseCase {

    public EnrollmentUseCase(){
    }

    Enrollment enroll(String userId, String routeId) {
        User user = new User(userId, "Sample User");
        Route route = new Route(routeId, "Sample Route");
        return new Enrollment(user, route);
    }
}
