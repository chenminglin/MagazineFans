package com.bethena.magazinefans.ui.all;

import com.bethena.magazinefans.Constants;
import com.bethena.magazinefans.di.ChildFragmentScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class NameModule {


    @ChildFragmentScoped
    @Provides
    String provideStartLetter(NameFragment fragment) {
        String startLetter = null;
        if (fragment.getArguments() != null) {
            startLetter = fragment.getArguments().getString(Constants.PARAM_KEY1);
        }
        return startLetter;
    }


}
