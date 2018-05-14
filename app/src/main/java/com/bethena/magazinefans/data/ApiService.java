package com.bethena.magazinefans.data;

import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.DataWrapper;
import com.bethena.magazinefans.bean.HomeData;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("index.php")
    Observable<DataWrapper<Banner>> getBanner(@Query("s") String s, @Query("p") String p);

    @POST("index.php")
    Observable<DataWrapper<HomeData>> getHomeList(@Query("s") String s, @Query("p") String p);
}
