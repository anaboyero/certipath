package com.certipath.domain;

import java.time.LocalDate;

public class Enrollment {
    User user;
    Route route;
    LocalDate enrolledAt;

    public Enrollment (User user, Route route) {
        this.user = user;
        this.route = route;
        this.enrolledAt = LocalDate.now();
    }

    public User getUser() {
        return user;
    }

    public Route getRoute() {
        return route;
    }

    public LocalDate getEnrolledAt() {
        return enrolledAt;
    }
}
