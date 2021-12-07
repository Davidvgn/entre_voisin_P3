package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import java.time.Instant;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AddToFavoriteActivity extends AppCompatActivity {
    @BindView(R.id.favorite_name)
    TextView favoriteName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_favorite);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String avatar = intent.getStringExtra("favorite_avatar");
        String name = intent.getStringExtra("favorite_name");
        String address = intent.getStringExtra("favorite_adress");
        String phone = intent.getStringExtra("favorite_phone");
        String aboutMe = intent.getStringExtra("favorite_aboutMe");

        ImageView mFavoriteAvatar = findViewById(R.id.favorite_avatar);
        TextView mFavoriteName = findViewById(R.id.favorite_name);
        TextView mFavoriteAdress = findViewById(R.id.favorite_address);
        TextView mFavoritePhone = findViewById(R.id.favorite_phone);
        TextView mFavoriteAboutMe = findViewById(R.id.favorite_aboutMe);
        TextView mFavoriteHeaderName = findViewById(R.id.favorite_header_name);
        Toolbar mToolbar = findViewById(R.id.fav_toolbar);

        Glide.with(this).asBitmap().load(avatar).into(mFavoriteAvatar);
        mFavoriteName.setText(name);
        mFavoriteAdress.setText(address);
        mFavoritePhone.setText(phone);
        mFavoriteAboutMe.setText(aboutMe);
        mFavoriteHeaderName.setText(name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}