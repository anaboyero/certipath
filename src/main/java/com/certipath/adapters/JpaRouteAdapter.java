package com.certipath.adapters;

import com.certipath.application.RoutePort;
import com.certipath.domain.Route;

import java.util.Optional;

public class JpaRouteAdapter implements RoutePort {
    private final RouteRepository routeRepository;

    public JpaRouteAdapter(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public boolean routeExists(String routeId) {
        return routeRepository.findById(routeId).isPresent();
    }

    @Override
    public Optional<Route> findRouteById(String routeId) {
        // de momento nada
        return Optional.empty();
    }

    @Override
    public void saveRoute(Route route) {
        // de momento nada
    }
}
