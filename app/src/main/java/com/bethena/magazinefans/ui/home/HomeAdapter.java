package com.bethena.magazinefans.ui.home;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.utils.ImageLoader;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<MagazineConcept, BaseViewHolder> {
    public HomeAdapter(@Nullable List<MagazineConcept> data) {
        super(R.layout.item_home, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, MagazineConcept item) {
        helper.setText(R.id.txt_title, item.magName);

        ImageLoader.loadCenterImg(mContext, item.magCover).into((ImageView) helper.getView(R.id.img_cover));

//        helper.addOnClickListener(R.id.img_cover);
    }
}
