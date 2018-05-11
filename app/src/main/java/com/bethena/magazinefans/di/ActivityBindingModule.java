package com.bethena.magazinefans.di;

import com.bethena.magazinefans.ui.MainActivity;
import com.bethena.magazinefans.ui.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
