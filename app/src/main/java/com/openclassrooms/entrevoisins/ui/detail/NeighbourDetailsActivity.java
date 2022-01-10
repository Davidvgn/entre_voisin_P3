package com.openclassrooms.entrevoisins.ui.detail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.NeighbourDetailActivityBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourRepository;

import java.util.List;


public class NeighbourDetailsActivity extends AppCompatActivity {

    private NeighbourDetailActivityBinding binding;
    private boolean isFavorite;
    NeighbourRepository mNeighbourRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNeighbourRepository = DI.getNeighbourRepository();

        binding = NeighbourDetailActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        boolean favoriteStatus = bundle.getBoolean("favoriteStatus");
        int neighbourIndex = bundle.getInt("neighbourIndex");
        long neighbourId = bundle.getLong("neighbourId");

        Intent intent = getIntent();
        Neighbour neighbour = mNeighbourRepository.getNeighbourById(neighbourId);

        String avatar = intent.getStringExtra("neighbour_detail_iv_avatar");
        String name = intent.getStringExtra("neighbour_detail_tv_name");
        String address = intent.getStringExtra("neighbour_detail_tv_address");
        String phone = intent.getStringExtra("neighbour_detail_tv_phone");
        String aboutMe = intent.getStringExtra("neighbour_detail_tv_aboutMe");

        Glide.with(this).asBitmap().load(avatar).into(binding.neighbourDetailIvAvatar);

        binding.neighbourDetailTvName.setText(name);
        binding.neighbourDetailTvAddress.setText(address);
        binding.neighbourDetailTvPhone.setText(phone);
        binding.neighbourDetailTvAboutMe.setText(aboutMe);
        Toolbar mToolbar = binding.toolbar;
        CollapsingToolbarLayout toolBarLayout = binding.neighbourDetailCtl;

        String neighbourName = name;
        toolBarLayout.setTitle(neighbourName);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = binding.neighbourDetailFabAddFavorite;

        if (!neighbour.getIsFavorite()) { //todo david un seul neighbour chercher comment faire fonctionner
            fab.setImageResource(R.drawable.ic_star_border_white_24dp);

        } else {
            fab.setImageResource(R.drawable.ic_star_yellow_24dp);

        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (neighbour.getIsFavorite() == false) {
                    Snackbar.make(view, "Ajouté à vos favoris", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    neighbour.setFavorite(true); //todo david appeler le repo


                    Toast.makeText(NeighbourDetailsActivity.this, "favorite : " + neighbour.getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(view, "Supprimé de vos favoris", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    neighbour.setFavorite(false);

                    Toast.makeText(NeighbourDetailsActivity.this, "favorite : " + neighbour.getIsFavorite(), Toast.LENGTH_SHORT).show();


                }
                if (view.getId() == R.id.neighbour_detail_fab_add_favorite) {
                    isFavorite = !isFavorite;
                    fab.setImageResource(isFavorite ? R.drawable.ic_star_yellow_24dp : R.drawable.ic_star_border_white_24dp);
                }
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
}