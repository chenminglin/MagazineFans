package com.bethena.magazinefans.ui.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.HomeData;
import com.bethena.magazinefans.utils.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomeData, BaseViewHolder> {
    public HomeAdapter(@Nullable List<HomeData> data) {
        super(R.layout.item_home, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, HomeData item) {
        helper.setText(R.id.txt_title, item.magName);

        ImageLoader.loadCenterImg(mContext, item.magCover).into((ImageView) helper.getView(R.id.img_cover));

        helper.addOnClickListener(R.id.img_cover);
    }
}
