package com.bethena.magazinefans.ui.maga;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bethena.magazinefans.Constants;
import com.bethena.magazinefans.R;
import com.bethena.magazinefans.core.BaseActivity;

import javax.inject.Inject;

public class MagaActivity extends BaseActivity {

    @Inject
    String mMagaId;

    @Inject
    MagaFragment mMagaFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maga);
        replaceFragment(R.id.fragment_container, mMagaFragment);
    }


    public static void start(Context context,String magaId){
        Intent intent = new Intent(context, MagaActivity.class);
        intent.putExtra(Constants.PARAM_KEY1, magaId);
        context.startActivity(intent);
    }


}
