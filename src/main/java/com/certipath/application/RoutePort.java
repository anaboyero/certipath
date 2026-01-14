package com.certipath.application;

import com.certipath.domain.Route;

import java.util.Optional;

public interface RoutePort {
    public Optional<Route> findRouteById(String routeId);
    public boolean routeExists(String routeId);
    public Route saveRoute(Route route);
}
