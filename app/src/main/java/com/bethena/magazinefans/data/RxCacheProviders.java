package com.bethena.magazinefans.data;

import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.DataWrapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKeyGroup;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;

public interface RxCacheProviders {
    @ProviderKey("home_banner")
    @LifeCache(duration = 60, timeUnit = TimeUnit.SECONDS)
    Observable<List<Banner>> getBanner(Observable<List<Banner>> bannerData, DynamicKeyGroup page);
}
