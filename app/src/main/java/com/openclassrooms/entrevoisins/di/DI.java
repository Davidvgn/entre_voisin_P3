package com.openclassrooms.entrevoisins.di;

import com.openclassrooms.entrevoisins.service.DummyNeighbourRepository;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static NeighbourRepository service = new DummyNeighbourRepository();

    /**
     * Get an instance on @{@link NeighbourRepository}
     * @return
     */
    public static NeighbourRepository getNeighbourRepository() {
        return service;
    }

    /**
     * Get always a new instance on @{@link NeighbourRepository}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static NeighbourRepository getNewInstanceNeighbourRepository() {
        return new DummyNeighbourRepository();
    }
}
