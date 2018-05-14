package com.bethena.magazinefans.data;

import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.DataWrapper;
import com.bethena.magazinefans.bean.HomeData;

import javax.inject.Inject;

import io.reactivex.Observable;

public class Repository  {

    ApiService mApiService;

    public Repository(ApiService apiService) {
        mApiService = apiService;
    }


    public Observable<DataWrapper<Banner>> getBanner() {
        return mApiService.getBanner("3", "0");
    }

    public Observable<DataWrapper<HomeData>> getHomeList(int page) {
        return mApiService.getHomeList("30", ""+page);
    }
}
