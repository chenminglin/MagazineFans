package com.bethena.magazinefans.ui;

import com.bethena.magazinefans.di.FragmentScoped;
import com.bethena.magazinefans.ui.all.AllFragment;
import com.bethena.magazinefans.ui.all.AllModule;
import com.bethena.magazinefans.ui.category.CateFragment;
import com.bethena.magazinefans.ui.home.HomeFragment;
import com.bethena.magazinefans.ui.home.HomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment homeFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CateFragment cateFragment();

    @FragmentScoped
    @ContributesAndroidInjector(modules = AllModule.class)
    abstract AllFragment allFragment();


}
