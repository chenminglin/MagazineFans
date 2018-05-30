package com.bethena.magazinefans.ui;

import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.core.BaseActivity;
import com.bethena.magazinefans.data.RxCacheProviders;
import com.bethena.magazinefans.ui.all.AllFragment;
import com.bethena.magazinefans.ui.category.CateFragment;
import com.bethena.magazinefans.ui.home.HomeFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import me.yokeyword.fragmentation.SupportFragment;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private static final String CURRENT_FRAGMENT_INDEX_KEY = "CURRENT_FRAGMENT_INDEX_KEY";

    @Inject
    HomeFragment mHomeFragment;

    @Inject
    CateFragment mCateFragment;

    @Inject
    AllFragment mAllFragment;

    Toolbar mToolbar;


    List<SupportFragment> mFragments = new ArrayList<>();
    int mFragmentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.tag(TAG).d("Environment.getDataDirectory() = "+Environment.getDataDirectory());
        Timber.tag(TAG).d("Environment.getExternalStorageState() = "+Environment.getExternalStorageState());
        Timber.tag(TAG).d("Environment.getDownloadCacheDirectory() = "+Environment.getDownloadCacheDirectory());
        Timber.tag(TAG).d("Environment.getExternalStorageDirectory() = "+Environment.getExternalStorageDirectory());
        Timber.tag(TAG).d("Environment.getRootDirectory() = "+Environment.getRootDirectory());
        Timber.tag(TAG).d("Environment.getDataDirectory() = "+Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS));
        Timber.tag(TAG).d("getCacheDir().toString() = "+getCacheDir().toString());
        Timber.tag(TAG).d("getExternalCacheDir().toString() = "+getExternalCacheDir().toString());

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        HomeFragment homeFragment = findFragment(HomeFragment.class);

        if (homeFragment != null) {
            mHomeFragment = homeFragment;
            mCateFragment = findFragment(CateFragment.class);
            mFragments.add(mHomeFragment);
            mFragments.add(mCateFragment);
            mFragments.add(mAllFragment);
        } else {
            mFragments.add(mHomeFragment);
            mFragments.add(mCateFragment);
            mFragments.add(mAllFragment);
            loadMultipleRootFragment(R.id.fl_container, mFragmentIndex, mHomeFragment, mCateFragment, mAllFragment);
        }

        if (savedInstanceState != null) {
            mFragmentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT_INDEX_KEY);
        }

        setToolbarTitleOfIndex(mFragmentIndex);
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
                    setToolbarTitleOfIndex(mFragmentIndex);
                    return true;
                case R.id.navigation_dashboard:
                    mFragmentIndex = 1;
                    showHideFragment(mFragments.get(mFragmentIndex), currentFragment);
                    setToolbarTitleOfIndex(mFragmentIndex);
                    return true;
                case R.id.navigation_notifications:
                    mFragmentIndex = 2;
                    showHideFragment(mFragments.get(mFragmentIndex), currentFragment);
                    setToolbarTitleOfIndex(mFragmentIndex);
                    return true;
                default:
                    mFragmentIndex = 0;
                    break;
            }

            showHideFragment(mFragments.get(mFragmentIndex), currentFragment);
            return false;
        }
    };

    public void setToolbarTitle(@StringRes int stringId) {
        mToolbar.setTitle(stringId);
    }


    public void setToolbarTitleOfIndex(int index) {
        switch (index) {
            case 0:
                setToolbarTitle(R.string.title_home);
                break;
            case 1:
                setToolbarTitle(R.string.title_category);
                break;
            case 2:
                setToolbarTitle(R.string.title_all);
                break;
            default:
                setToolbarTitle(R.string.title_home);
                break;
        }
    }
}
