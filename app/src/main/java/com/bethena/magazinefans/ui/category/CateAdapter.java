package com.bethena.magazinefans.ui.category;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bethena.magazinefans.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import timber.log.Timber;

public class CateAdapter extends BaseQuickAdapter<CateViewModel, BaseViewHolder> {


    ArrayMap<Integer, CateItemAdapter> cateItemAdapterMap = new ArrayMap();

    ArrayMap<Integer, Integer> mListViewXMap = new ArrayMap<>();


    public CateAdapter(@Nullable List<CateViewModel> data) {
        super(R.layout.item_cate, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CateViewModel item) {

        helper.setText(R.id.tv_cate_title, item.getCategory().cateName);

        RecyclerView recyclerView = helper.getView(R.id.recycler_view);

        CateItemAdapter itemAdapter;

        if ((itemAdapter = cateItemAdapterMap.get(item.getCategory().cateId)) == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
            itemAdapter = new CateItemAdapter(item.getMagas());
            recyclerView.setAdapter(itemAdapter);
            itemAdapter.loadMoreComplete();
            itemAdapter.loadMoreEnd(false);
            cateItemAdapterMap.put(item.getCategory().cateId, itemAdapter);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    Timber.d(recyclerView.hashCode() + "    dx = " + dx);
                    Integer x = mListViewXMap.get(recyclerView.hashCode());
                    Timber.d(recyclerView.hashCode() + "    allx = " + x);
                    if (x != null) {
                        mListViewXMap.put(recyclerView.hashCode(), (dx + x));
                    } else {
                        mListViewXMap.put(recyclerView.hashCode(), dx);
                    }


                }
            });
        } else {
            Integer dx = mListViewXMap.get(recyclerView.hashCode());
            if (dx != null) {
                recyclerView.scrollTo(dx, 0);
            }
        }


        helper.addOnClickListener(R.id.btn_more);

    }
}
