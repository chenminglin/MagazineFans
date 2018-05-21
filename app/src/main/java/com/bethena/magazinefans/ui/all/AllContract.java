package com.bethena.magazinefans.ui.all;

import com.bethena.magazinefans.core.IPresenter;
import com.bethena.magazinefans.core.IView;

public interface AllContract {

    interface View extends IView {

    }

    interface Presenter extends IPresenter<AllContract.View> {

    }

}
