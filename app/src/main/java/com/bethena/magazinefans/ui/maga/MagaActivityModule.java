package com.bethena.magazinefans.ui.maga;

import com.bethena.magazinefans.Constants;
import com.bethena.magazinefans.di.ActivityScoped;
import com.bethena.magazinefans.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import timber.log.Timber;

@Module
public abstract class MagaActivityModule {
    @ContributesAndroidInjector
    @FragmentScoped
    abstract MagaFragment magaFragment();


    @ActivityScoped
    @Provides
    static String provideMagaId(MagaActivity magaActivity) {
        Timber.d("magaId = " + magaActivity.getIntent().getStringExtra(Constants.PARAM_KEY1));
        return magaActivity.getIntent().getStringExtra(Constants.PARAM_KEY1);
    }

}
