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
public class DataModule {

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(OkHttpClient.Builder httpBuilder) {
        return httpBuilder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.client(okHttpClient);
        retrofitBuilder.baseUrl(BuildConfig.BASE_URL);
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));
        return retrofitBuilder;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    Repository provideRepository(ApiService apiService) {
        return new Repository(apiService);
    }
}
