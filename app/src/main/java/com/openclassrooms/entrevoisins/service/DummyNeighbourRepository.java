package com.openclassrooms.entrevoisins.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourRepository implements NeighbourRepository {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    Neighbour mNeighbour;


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
        List<Neighbour> favoriteNeighbours = new ArrayList<>(getNeighbours());
        favoriteNeighbours.removeIf(neighbour -> !neighbour.getIsFavorite());
        return favoriteNeighbours;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Neighbour getNeighbourById(long id) {
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getId() == id) {
                mNeighbour = neighbour;
            }
        }
        return mNeighbour;
    }


    @Override
    public void toggleFavoriteNeighbour(long neighbour) {
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
