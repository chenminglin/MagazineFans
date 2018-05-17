package com.bethena.magazinefans.data;

import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.Category;
import com.bethena.magazinefans.bean.DataWrapper;
import com.bethena.magazinefans.bean.MagazineConcept;

import javax.annotation.Nullable;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("index.php")
    Observable<DataWrapper<Banner>> getBanner(@Query("s") int size, @Query("p") int page);

    @POST("index.php")
    Observable<DataWrapper<MagazineConcept>> getHomeList(@Query("s") int size, @Query("p") int page);

    @POST("cate.php")
    Observable<DataWrapper<Category>> getCategory();

    @POST("lists.php")
    Observable<DataWrapper<MagazineConcept>> getMagazinesByCate(
            @Query("s") int size,
            @Query("p") int page,
            @Query("c") int cate,
            @Nullable @Query("m") Integer brand);
}
