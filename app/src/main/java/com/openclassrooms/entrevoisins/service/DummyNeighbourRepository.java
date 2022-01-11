package com.openclassrooms.entrevoisins.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.android.material.snackbar.Snackbar;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourRepository implements NeighbourRepository {

    private final List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

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

    @Override
    public Neighbour getNeighbourById(long id) {
        Neighbour neighbour = null;

        for (Neighbour item : neighbours) {
            if (item.getId() == id) {
                neighbour = item;
                break;
            }
        }

        return neighbour;
    }


    @Override
    public void toggleFavoriteNeighbour(long neighbourId) {
        Neighbour currentNeighbour = getNeighbourById(neighbourId);
        currentNeighbour.setFavorite(!currentNeighbour.getIsFavorite());
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
