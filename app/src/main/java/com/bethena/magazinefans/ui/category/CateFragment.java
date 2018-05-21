package com.bethena.magazinefans.ui.category;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.core.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import javax.inject.Inject;


public class CateFragment extends BaseFragment<CatePresenter> implements CateContract.View {


    RecyclerView mRecyclerView;

    CateAdapter mAdapter;

    @Inject
    public CateFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        mAdapter = new CateAdapter(null);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_more:
                        CateViewModel viewModel = mPresenter.getModels().get(position);
//                        viewModel.getCategory().cateId;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadData();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoadDataComplete(List<CateViewModel> modelList) {
        mAdapter.setNewData(modelList);
        mAdapter.loadMoreComplete();
        mAdapter.loadMoreEnd(true);
    }
}
