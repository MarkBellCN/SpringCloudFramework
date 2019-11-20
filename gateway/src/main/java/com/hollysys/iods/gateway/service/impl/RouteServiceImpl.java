package com.hollysys.iods.gateway.service.impl;

import com.hollysys.iods.gateway.service.RouteService;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

public class RouteServiceImpl implements RouteService {
    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return null;
    }

    @Override
    public boolean save(RouteDefinition routeDefinition) {
        return false;
    }

    @Override
    public boolean delete(String routeId) {
        return false;
    }
}
