package com.bethena.magazinefans.ui.home;

import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.HomeData;
import com.bethena.magazinefans.core.IPresenter;
import com.bethena.magazinefans.core.IView;

import java.util.List;

public interface HomeContract {

    interface View extends IView<HomeContract.Presenter> {
        void onLoadBannerComplete(List<Banner> banners);

        void onLoadListComplete(List<HomeData> homeDatas, boolean isRefresh);

        void onEmpty();

        void onLoadNoMore();
    }

    interface Presenter extends IPresenter<HomeContract.View> {
        void loadBanner();

        void refreshList();

        void loadMoreList();

        List<HomeData> getHomeDatas();

    }
}
