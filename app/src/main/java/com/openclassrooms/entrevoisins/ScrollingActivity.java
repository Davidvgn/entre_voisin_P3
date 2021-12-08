package com.openclassrooms.entrevoisins;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

import com.openclassrooms.entrevoisins.databinding.NeighbourDetailActivityBinding;

public class ScrollingActivity extends AppCompatActivity {

    private NeighbourDetailActivityBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = NeighbourDetailActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.neighbourDetailCtl;
        toolBarLayout.setTitle(getTitle());

        ImageView neighbourImage = binding.neighbourDetailIvAvatar;
        Glide.with(neighbourImage).load("https://i.pravatar.cc/150?u=a042581f3e39026702d").into(neighbourImage);

        FloatingActionButton fab = binding.neighbourDetailFabAddFavorite;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
    }
}