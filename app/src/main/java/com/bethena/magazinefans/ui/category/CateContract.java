package com.bethena.magazinefans.ui.category;

import com.bethena.magazinefans.core.IPresenter;
import com.bethena.magazinefans.core.IView;

public interface CateContract {

    interface View extends IView<CateContract.Presenter>{

    }

    interface Presenter extends IPresenter<CateContract.View>{

    }
}
