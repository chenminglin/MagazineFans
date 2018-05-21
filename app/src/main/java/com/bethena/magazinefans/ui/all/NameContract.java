package com.bethena.magazinefans.ui.all;

import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.core.IPresenter;
import com.bethena.magazinefans.core.IView;

import java.util.List;

public interface NameContract {

    interface View extends IView {
        void onLoadDatasComplete(List<MagazineConcept> datas);

        void onEmpty();

        void onLoadNoMore();

        void onLoadListComplete(List<MagazineConcept> magazineConcepts, boolean isRefresh);
    }

    interface Presenter extends IPresenter<NameContract.View> {
        List<MagazineConcept> getDatas();

        void refreshList();

        void loadMoreList();

    }
}
