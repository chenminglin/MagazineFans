package com.bethena.magazinefans.ui.all;

import com.bethena.magazinefans.di.ChildFragmentScoped;
import com.bethena.magazinefans.di.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AllModule {
    @ChildFragmentScoped
    @ContributesAndroidInjector
    abstract NameFragment nameFragment();
}
