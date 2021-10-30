package com.random.ramisguide.NetworkAdsManager.Ads;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.R;
import com.squareup.picasso.Picasso;

public class CpaInter extends Dialog {
    public Dialog dialog;
    Context mcontext;

    public CpaInter(@NonNull Context context) {
        super(context);
        this.mcontext = context;
    }

    public CpaInter(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mcontext = context;

    }

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cpa_inter);
        Picasso.get().load(Config.controls.getCpa().getImg()).into((ImageView) findViewById(R.id.img));
        findViewById(R.id.close).setOnClickListener(v -> {
            CpaInter.this.dismiss();
        });
        findViewById(R.id.img).setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Config.controls.getCpa().getUrl()));
            mcontext.startActivity(browserIntent);
        });
    }


}
