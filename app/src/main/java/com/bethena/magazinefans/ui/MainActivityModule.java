package com.bethena.magazinefans.ui;

import com.bethena.magazinefans.data.Repository;
import com.bethena.magazinefans.di.ActivityScoped;
import com.bethena.magazinefans.di.FragmentScoped;
import com.bethena.magazinefans.ui.home.HomeContract;
import com.bethena.magazinefans.ui.home.HomeFragment;
import com.bethena.magazinefans.ui.home.HomeModule;
import com.bethena.magazinefans.ui.home.HomePresenter;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeFragment homeFragment();


//    @ActivityScoped
//    @Provides
//    static HomeFragment homeFragment(){
//        return new HomeFragment();
//    }

//    @Binds
//    @ActivityScoped
//    abstract HomeContract.View bindView(HomeFragment homeFragment);


    @Provides
    @ActivityScoped
    static HomeContract.Presenter providePresenter(Repository repository) {
        return new HomePresenter(repository);
    }
}
