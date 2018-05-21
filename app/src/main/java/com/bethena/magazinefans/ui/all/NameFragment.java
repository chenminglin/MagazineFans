package com.bethena.magazinefans.ui.all;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bethena.magazinefans.Constants;
import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.core.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import javax.inject.Inject;

public class NameFragment extends BaseFragment<NamePresenter> implements NameContract.View,
        BaseQuickAdapter.RequestLoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {
    @Override
    public void onError() {

    }

    @Inject
    String mChat;

    SwipeRefreshLayout mRefreshLayout;
    RecyclerView mRecyclerView;

    NameAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        mAdapter = new NameAdapter(mPresenter.getDatas());

        mAdapter.setOnLoadMoreListener(this, mRecyclerView);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        mPresenter.refreshList();

    }

    public static NameFragment newInstance(String chat) {
        Bundle args = new Bundle();
        args.putString(Constants.PARAM_KEY1, chat);
        NameFragment fragment = new NameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onLoadDatasComplete(List<MagazineConcept> datas) {
        mAdapter.setNewData(datas);
        mAdapter.loadMoreComplete();
        mAdapter.loadMoreEnd();
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onLoadNoMore() {
        mAdapter.loadMoreEnd();
    }

    @Override
    public void onLoadListComplete(List<MagazineConcept> magazineConcepts, boolean isRefresh) {
        if (isRefresh) {
            mRefreshLayout.setRefreshing(false);
            mAdapter.setNewData(magazineConcepts);
        } else {
            mAdapter.addData(magazineConcepts);
        }

        mAdapter.loadMoreComplete();
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMoreList();
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshList();
    }
}
