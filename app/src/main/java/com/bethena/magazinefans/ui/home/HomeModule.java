package com.bethena.magazinefans.ui.home;

import com.bethena.magazinefans.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class HomeModule {


    @Binds
    @FragmentScoped
    abstract HomeContract.View bindView(HomeFragment homeFragment);


    @Binds
    @FragmentScoped
    abstract HomeContract.Presenter homePresenter(HomePresenter homePresenter);


}
