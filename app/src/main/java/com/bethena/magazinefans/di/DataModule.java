package com.bethena.magazinefans.di;

import com.bethena.magazinefans.BuildConfig;
import com.bethena.magazinefans.data.ApiService;
import com.bethena.magazinefans.data.Repository;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class DataModule {

    @Provides
    @Singleton
    static OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(OkHttpClient.Builder httpBuilder) {
        return httpBuilder.build();
    }

    @Provides
    @Singleton
    static Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    static Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.client(okHttpClient);
        retrofitBuilder.baseUrl(BuildConfig.BASE_URL);
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        return retrofitBuilder;
    }

    @Provides
    @Singleton
    static Retrofit provideRetrofit(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build();
    }

    @Provides
    @Singleton
    static ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    static Repository provideRepository(ApiService apiService) {
        return new Repository(apiService);
    }
}
