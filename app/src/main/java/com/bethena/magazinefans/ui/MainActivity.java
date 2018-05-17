package com.bethena.magazinefans.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.core.BaseActivity;
import com.bethena.magazinefans.ui.category.CateFragment;
import com.bethena.magazinefans.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends BaseActivity {

    private static final String CURRENT_FRAGMENT_INDEX_KEY = "CURRENT_FRAGMENT_INDEX_KEY";

    @Inject
    HomeFragment mHomeFragment;

    @Inject
    CateFragment mCateFragment;


    List<SupportFragment> mFragments = new ArrayList<>();
    int mFragmentIndex = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        HomeFragment homeFragment = findFragment(HomeFragment.class);

        if (homeFragment != null) {
            mHomeFragment = homeFragment;
            mCateFragment = findFragment(CateFragment.class);
            mFragments.add(mHomeFragment);
            mFragments.add(mCateFragment);
        } else {
            mFragments.add(mHomeFragment);
            mFragments.add(mCateFragment);
            loadMultipleRootFragment(R.id.fl_container, mFragmentIndex, mHomeFragment, mCateFragment);
        }

        if (savedInstanceState != null) {
            mFragmentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT_INDEX_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_FRAGMENT_INDEX_KEY, mFragmentIndex);
        super.onSaveInstanceState(outState);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            SupportFragment currentFragment = mFragments.get(mFragmentIndex);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mFragmentIndex = 0;
                    showHideFragment(mFragments.get(mFragmentIndex), currentFragment);
                    return true;
                case R.id.navigation_dashboard:
                    mFragmentIndex = 1;
                    showHideFragment(mFragments.get(mFragmentIndex), currentFragment);
                    return true;
                case R.id.navigation_notifications:
                    mFragmentIndex = 2;
                    showHideFragment(mFragments.get(mFragmentIndex), currentFragment);
                    return true;
                default:
                    mFragmentIndex = 0;
                    break;
            }

            showHideFragment(mFragments.get(mFragmentIndex), currentFragment);
            return false;
        }
    };

}
