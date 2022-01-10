package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.NeighbourClickedEvent;
import com.openclassrooms.entrevoisins.ui.detail.NeighbourDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }

    @OnClick(R.id.add_neighbour)
    void addNeighbour() {
        AddNeighbourActivity.navigate(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onNeighbourClicked(NeighbourClickedEvent event) {
        String avatar = event.neighbour.getAvatarUrl();
        String name = event.neighbour.getName();
        String address = event.neighbour.getAddress();
        String phone = event.neighbour.getPhoneNumber();
        String aboutMe = event.neighbour.getAboutMe();
        Long id = event.neighbour.getId();

        Intent myIntent = new Intent(this, NeighbourDetailsActivity.class);
        myIntent.putExtra("neighbour_detail_iv_avatar", avatar);
        myIntent.putExtra("neighbour_detail_tv_name", name);
        myIntent.putExtra("neighbour_detail_tv_address", address);
        myIntent.putExtra("neighbour_detail_tv_phone", phone);
        myIntent.putExtra("neighbour_detail_tv_aboutMe", aboutMe);
        myIntent.putExtra("neighbourId", id);

        Boolean favoriteBoolean = event.neighbour.getIsFavorite();
        myIntent.putExtra("favoriteStatus", favoriteBoolean);

        startActivity(myIntent);
    }


}
