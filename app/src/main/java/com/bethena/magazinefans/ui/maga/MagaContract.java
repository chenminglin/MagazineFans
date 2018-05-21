package com.bethena.magazinefans.ui.maga;

import com.bethena.magazinefans.bean.MagazineDetail;
import com.bethena.magazinefans.core.IPresenter;
import com.bethena.magazinefans.core.IView;

import java.util.List;

public interface MagaContract {

    interface View extends IView{

        void onLoadMagaComplete(List<MagazineDetail> magas);

    }

    interface Presenter extends IPresenter<MagaContract.View>{
        void loadMaga();
    }
}
