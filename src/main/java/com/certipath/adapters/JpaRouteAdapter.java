package com.certipath.adapters;

import com.certipath.adapters.entities.RouteEntity;
import com.certipath.application.RoutePort;
import com.certipath.domain.Route;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
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
        Optional<RouteEntity> result = routeRepository.findById(routeId);
        if(result.isPresent()) {
            return Optional.of(new Route(result.get().getId(), result.get().getName()));
        }
        return Optional.empty();
    }

    @Override
    public Route saveRoute(Route route) {
        RouteEntity savedRouteEntity = routeRepository.save(new RouteEntity(route.getName()));
        return new Route(savedRouteEntity.getId(), route.getName());
    }
}
