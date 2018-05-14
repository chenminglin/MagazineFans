package com.bethena.magazinefans.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.HomeData;
import com.bethena.magazinefans.core.BaseFragment;

import java.util.List;

import javax.inject.Inject;


public class HomeFragment extends BaseFragment<HomeContract.Presenter> implements HomeContract.View {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRefreshLayout;
    HomeAdapter mHomeAdapter;

    @Inject
    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);

        mHomeAdapter = new HomeAdapter(mPresenter.getHomeDatas());

        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));

        mRecyclerView.setAdapter(mHomeAdapter);

        mPresenter.loadList();


    }


    @Override
    public void onError() {

    }




    @Override
    public void onLoadBannerComplete(List<Banner> banners) {

    }

    @Override
    public void onLoadListComplete(List<HomeData> homeDatas, boolean isRefresh) {
        if (isRefresh) {
            mHomeAdapter.setNewData(homeDatas);
        } else {
            mHomeAdapter.addData(homeDatas);
        }

        mHomeAdapter.loadMoreComplete();
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onLoadNoMore() {
        mHomeAdapter.loadMoreEnd(true);
    }
}
