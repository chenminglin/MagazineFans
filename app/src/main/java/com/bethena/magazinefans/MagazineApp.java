package com.bethena.magazinefans;

import android.app.Application;

import com.bethena.magazinefans.di.DaggerAppComponent;
import com.facebook.stetho.Stetho;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import timber.log.Timber;

public class MagazineApp extends DaggerApplication {

    @Inject
    Gson gson;

    private static MagazineApp mApp;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    public static Application get(){
        return mApp;
    }

    @Override
    public void onCreate() {
        mApp = this;
        super.onCreate();
        Timber.plant(new Timber.DebugTree());


        Timber.d(gson.toString());

        Stetho.initializeWithDefaults(this);

    }
}
