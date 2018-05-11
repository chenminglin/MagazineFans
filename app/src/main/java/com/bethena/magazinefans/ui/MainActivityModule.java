package com.bethena.magazinefans.ui;

import com.bethena.magazinefans.di.ActivityScoped;
import com.bethena.magazinefans.di.FragmentScoped;
import com.bethena.magazinefans.ui.home.HomeFragment;
import com.bethena.magazinefans.ui.home.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment homeFragment();
}
