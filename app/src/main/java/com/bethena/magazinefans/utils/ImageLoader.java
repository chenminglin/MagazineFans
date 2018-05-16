package com.bethena.magazinefans.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.util.LruCache;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoader {

    private static LruCache<String, RequestOptions> GLIDE_OPTION_CACHE = new LruCache<String, RequestOptions>(10);

    private static String OPTION_CENTER_IMG = "OPTION_CENTER_IMG";

    public static RequestBuilder<Drawable> loadCenterImg(Context context, String url) {
        RequestOptions options;
        if ((options = getOption(OPTION_CENTER_IMG)) == null) {
            options = new RequestOptions().centerCrop();
        }

        return Glide.with(context)
                .load(url)
                .apply(options);

    }

    private static RequestOptions getOption(String key) {
        return GLIDE_OPTION_CACHE.get(key);
    }
}
