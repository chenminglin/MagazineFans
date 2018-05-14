package com.bethena.magazinefans;

import com.bethena.magazinefans.data.Repository;
import com.bethena.magazinefans.di.DaggerAppComponent;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class MagazineApp extends DaggerApplication {

    @Inject
    Gson gson;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());


        Timber.d(gson.toString());

    }
}
