package com.openclassrooms.entrevoisins.ui.detail;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.NeighbourDetailActivityBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

public class NeighbourDetailsActivity extends AppCompatActivity {

    private NeighbourDetailActivityBinding binding;
    NeighbourRepository mNeighbourRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNeighbourRepository = DI.getNeighbourRepository();

        binding = NeighbourDetailActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        long neighbourId = bundle.getLong("neighbourId");

        Neighbour neighbour = mNeighbourRepository.getNeighbourById(neighbourId);

        Glide.with(this).asBitmap().load(neighbour.getAvatarUrl()).into(binding.neighbourDetailIvAvatar);

        String fakeNetwork = "www.facebook.com/" + neighbour.getName();
        binding.neighbourDetailTvName.setText(neighbour.getName());
        binding.neighbourDetailTvAddress.setText(neighbour.getAddress());
        binding.neighbourDetailTvPhone.setText(neighbour.getPhoneNumber());
        binding.neighbourDetailTvAboutMe.setText(neighbour.getAboutMe());
        binding.neighbourDetailTvNetwork.setText(fakeNetwork);
        Toolbar mToolbar = binding.toolbar;
        CollapsingToolbarLayout toolBarLayout = binding.neighbourDetailCtl;

        String neighbourName = neighbour.getName();
        toolBarLayout.setTitle(neighbourName);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setStarFilled(neighbour.getIsFavorite());

        binding.neighbourDetailFabAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mNeighbourRepository.toggleFavoriteNeighbour(neighbourId);

                if (neighbour.getIsFavorite()) {
                    Snackbar.make(view, "Ajouté(e) à vos favoris", Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(view, "Supprimé(e) de vos favoris", Snackbar.LENGTH_LONG).show();
                }

                setStarFilled(neighbour.getIsFavorite());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setStarFilled(boolean filled) {
        if (filled) {
            binding.neighbourDetailFabAddFavorite.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            binding.neighbourDetailFabAddFavorite.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }
}