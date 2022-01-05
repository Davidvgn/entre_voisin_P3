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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.databinding.NeighbourDetailActivityBinding;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;


public class NeighbourDetailsActivity extends AppCompatActivity {

    private NeighbourDetailActivityBinding binding;
    private boolean isFavorite;
    private SharedPreferences settings;

     List<Neighbour> mNeighbours;
     NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        mNeighbours = mApiService.getNeighbours();


//        //todo david test
//        this.settings = getPreferences(MODE_PRIVATE);
//        this.isFavorite = settings.getBoolean("isFavorite", false);


        binding = NeighbourDetailActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String avatar = intent.getStringExtra("neighbour_detail_iv_avatar");
        String name = intent.getStringExtra("neighbour_detail_tv_name");
        String address = intent.getStringExtra("neighbour_detail_tv_address");
        String phone = intent.getStringExtra("neighbour_detail_tv_phone");
        String aboutMe = intent.getStringExtra("neighbour_detail_tv_aboutMe");

        ImageView mFavoriteAvatar = binding.neighbourDetailIvAvatar;
        TextView mFavoriteName = binding.neighbourDetailTvName;
        TextView mFavoriteAddress = binding.neighbourDetailTvAddress;
        TextView mFavoritePhone = binding.neighbourDetailTvPhone;
        TextView mFavoriteAboutMe = binding.neighbourDetailTvAboutMe;
        Toolbar mToolbar = binding.toolbar;
        CollapsingToolbarLayout toolBarLayout = binding.neighbourDetailCtl;
        Glide.with(this).asBitmap().load(avatar).into(mFavoriteAvatar);

        mFavoriteName.setText(name);
        mFavoriteAddress.setText(address);
        mFavoritePhone.setText(phone);
        mFavoriteAboutMe.setText(aboutMe);

        String neighbourName = name;
        toolBarLayout.setTitle(neighbourName);



        //todo david test
        Bundle bundle = getIntent().getExtras();
        boolean favoriteStatus = bundle.getBoolean("favoriteStatus");
        int neighbourIndex = bundle.getInt("neighbourIndex");


        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = binding.neighbourDetailFabAddFavorite;

//        if (isFavorite == false) {
//            fab.setImageResource(R.drawable.ic_star_border_white_24dp);
//
//        } else {
//            fab.setImageResource(R.drawable.ic_star_yellow_24dp);
//
//        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mNeighbours.get(neighbourIndex).getIsFavorite() == false) {
                    Snackbar.make(view, "Ajouté à vos favoris", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    mNeighbours.get(neighbourIndex).setFavorite(true);


                    Toast.makeText(NeighbourDetailsActivity.this, "favorite : "  + mNeighbours.get(neighbourIndex).getIsFavorite(), Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(view, "Supprimé de vos favoris", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    mNeighbours.get(neighbourIndex).setFavorite(false);

                    Toast.makeText(NeighbourDetailsActivity.this, "index : " + mNeighbours.get(neighbourIndex).getIsFavorite(), Toast.LENGTH_SHORT).show();


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

//    //todo david test
//    @Override
//    protected void onPause() {
//        super.onPause();

//        SharedPreferences.Editor editor = this.settings.edit();
//        editor.putBoolean("isFavorite", this.isFavorite);
//        editor.commit();
//    }
//
//    //todo david test
    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = this.settings.edit();
        editor.putBoolean("isFavorite", this.isFavorite);

        editor.commit();
    }
}