package com.bethena.magazinefans.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private ViewPager mViewPager;
    @Inject
    HomeFragment mHomeFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mViewPager.setCurrentItem(2);
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mViewPager = findViewById(R.id.viewpager);

        HomeFragment homeFragment1 = new HomeFragment();
        HomeFragment homeFragment2 = new HomeFragment();

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mHomeFragment);
        fragments.add(homeFragment1);
        fragments.add(homeFragment2);

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(mainPagerAdapter);

        mViewPager.setOffscreenPageLimit(3);

    }

}
