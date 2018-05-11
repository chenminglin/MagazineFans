package com.bethena.magazinefans.ui.home;

import com.bethena.magazinefans.core.IPresenter;
import com.bethena.magazinefans.core.IView;

public interface HomeContract {

    interface View extends IView<HomeContract.Presenter> {
        void onLoadDataComplete();
    }

    interface Presenter extends IPresenter<HomeContract.View> {
        void loadData();
    }
}
