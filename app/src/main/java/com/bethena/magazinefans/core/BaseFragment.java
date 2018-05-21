package com.bethena.magazinefans.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;
import dagger.android.support.HasSupportFragmentInjector;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseFragment<P extends IPresenter> extends SupportFragment implements HasSupportFragmentInjector, IView {

    protected final String TAG = getClass().getCanonicalName();

    @Inject
    public P mPresenter;

    @Inject
    DispatchingAndroidInjector<Fragment> childFragmentInjector;

    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
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


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return childFragmentInjector;
    }
}
