package com.bethena.magazinefans.core;

import android.app.Activity;
import android.content.Context;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<P extends IPresenter> extends DaggerFragment implements IView<P> {

    @Inject
    public P mPresenter;

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        mActivity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.detachView();
        }

        mActivity = null;
    }
}
