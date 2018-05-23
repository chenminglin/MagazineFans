package com.bethena.magazinefans.ui.home;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bethena.magazinefans.MagazineApp;
import com.bethena.magazinefans.R;
import com.bethena.magazinefans.bean.MagazineConcept;
import com.bethena.magazinefans.utils.ImageLoader;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<MagazineConcept, HomeAdapter.HomeViewHolder> {
    public HomeAdapter(@Nullable List<MagazineConcept> data) {
        super(R.layout.item_home, data);

    }

    @Override
    protected void convert(HomeViewHolder helper, MagazineConcept item) {
        helper.setText(R.id.txt_title, item.magName);

        ImageLoader.loadCenterImg(mContext, item.magCover).into((ImageView) helper.getView(R.id.img_cover));

//        helper.addOnClickListener(R.id.img_cover);
    }

    class HomeViewHolder extends BaseViewHolder {

        View mView;

        public HomeViewHolder(View view) {
            super(view);
            mView = view;
        }

        public void releaseImages() {
            if (mView instanceof ImageView) {
                ImageLoader.releaseImg(MagazineApp.get(), (ImageView) mView);
            } else if (mView instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) mView;
                viewGroup.getChildCount();

                for (int n = 0; n <= viewGroup.getChildCount(); n++) {
                    View childView = viewGroup.getChildAt(n);

                    if (childView != null && childView instanceof ImageView) {
                        ImageLoader.releaseImg(MagazineApp.get(), (ImageView) childView);
                    }
                }
            }
        }

    }
}
