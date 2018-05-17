package com.bethena.magazinefans.ui.category;

import com.bethena.magazinefans.core.IPresenter;
import com.bethena.magazinefans.core.IView;

import java.util.List;

public interface CateContract {

    interface View extends IView<CateContract.Presenter>{
        void onLoadDataComplete(List<CateViewModel> modelList);
    }

    interface Presenter extends IPresenter<CateContract.View>{
        void loadData();

        List<CateViewModel> getModels();
    }
}
