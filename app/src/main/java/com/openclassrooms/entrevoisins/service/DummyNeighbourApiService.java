package com.openclassrooms.entrevoisins.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {

        return neighbours;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        getNeighbours().removeIf(neighbour -> neighbour.getIsFavorite() == false);
        return neighbours;
    }


    @Override
    public void toggleFavoriteNeighbour(Neighbour neighbour) {
        if (neighbour.getIsFavorite() == false) {
            neighbour.setFavorite(true);
        } else {
            neighbour.setFavorite(false);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }
}
