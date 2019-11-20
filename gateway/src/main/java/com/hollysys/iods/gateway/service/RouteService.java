package com.hollysys.iods.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface RouteService {
    Collection<RouteDefinition> getRouteDefinitions();

    boolean save(RouteDefinition routeDefinition);

    boolean delete(String routeId);
}
