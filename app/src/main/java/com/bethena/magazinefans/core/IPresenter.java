package com.bethena.magazinefans.core;

public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView();
}
