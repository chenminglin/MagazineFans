package com.bethena.magazinefans.di;

import android.app.Application;

import com.bethena.magazinefans.MagazineApp;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {AppModule.class
        , AndroidSupportInjectionModule.class
        , ActivityBindingModule.class})
public interface AppComponent extends AndroidInjector<MagazineApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
