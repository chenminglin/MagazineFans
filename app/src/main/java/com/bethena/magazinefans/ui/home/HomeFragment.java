package com.bethena.magazinefans.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bethena.magazinefans.Constants;
import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.Banner;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.core.BaseFragment;
import com.bethena.magazinefans.ui.MainActivity;
import com.bethena.magazinefans.ui.maga.MagaActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class HomeFragment extends BaseFragment<HomePresenter> implements
        HomeContract.View,
        BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRefreshLayout;
    HomeAdapter mHomeAdapter;

    View mHeaderView;

    ViewPager mBannerViewPager;


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
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mHeaderView = mActivity.getLayoutInflater().inflate(R.layout.banner_home, (ViewGroup) mRecyclerView.getParent(), false);

        mBannerViewPager = mHeaderView.findViewById(R.id.view_pager_banner);

        mRefreshLayout.setOnRefreshListener(this);

        mHomeAdapter = new HomeAdapter(mPresenter.getHomeDatas());

        mRecyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
        mRecyclerView.setAdapter(mHomeAdapter);
        mHomeAdapter.setOnLoadMoreListener(this, mRecyclerView);


        mHomeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MagazineConcept magazineConcept = mPresenter.getHomeDatas().get(position);
                MagaActivity.start(mActivity, magazineConcept.magId);
            }
        });

        mHomeAdapter.addHeaderView(mHeaderView);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.refreshList();
        mPresenter.loadBanner();
    }

    @Override
    public void onError() {
        mRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onLoadBannerComplete(final List<Banner> banners) {
        BannerAdapter bannerAdapter = new BannerAdapter(banners);
        mBannerViewPager.setAdapter(bannerAdapter);
        mBannerViewPager.setOffscreenPageLimit(banners.size());

    }

    @Override
    public void onLoadListComplete(List<MagazineConcept> magazineConcepts, boolean isRefresh) {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }

        if (isRefresh) {
            mHomeAdapter.setNewData(magazineConcepts);
        } else {
            mHomeAdapter.addData(magazineConcepts);
        }

        mHomeAdapter.loadMoreComplete();
    }

    @Override
    public void onEmpty() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadNoMore() {
        mHomeAdapter.loadMoreEnd(true);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMoreList();
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshList();
        mPresenter.loadBanner();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        int count = mRecyclerView.getChildCount();
        Timber.tag(TAG).d("getChildCount = " + count);

        for (int position = 0; position <= count; position++) {
            View itemView = mRecyclerView.getChildAt(position);
            Timber.tag(TAG).d("position = " + position);
            if (itemView == null) {
                continue;
            }
            RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(itemView);
            if (viewHolder instanceof HomeAdapter.HomeViewHolder) {
//                viewHolder
                ((HomeAdapter.HomeViewHolder) viewHolder).releaseImages();
            }
            Timber.tag(TAG).d("viewHolder = " + viewHolder);
        }

    }
}
