package com.random.ramisguide.Guide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ironsource.mediationsdk.IronSource;
import com.random.ramisguide.Modules.GuideModule;
import com.random.ramisguide.MyApplication;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.R;
import com.squareup.picasso.Picasso;

public class GuideView extends AppCompatActivity {
    MyApplication adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_view);
        adManager = (MyApplication) getApplicationContext();
        adManager.initAds(this);
        adManager.ShowBanner(findViewById(R.id.banner));
        findViewById(R.id.goback).setOnClickListener(v -> {
            finish();
        });
        findViewById(R.id.back).setOnClickListener(v -> {
            finish();
        });
        int Position = getIntent().getIntExtra("position", 0);
        GuideModule guideModule = Config.controls.getGuide().get(Position);
        Picasso.get().load(guideModule.getImg()).into((ImageView) findViewById(R.id.img));
        ((TextView) findViewById(R.id.text)).setText(guideModule.getContent());
        ((TextView) findViewById(R.id.title)).setText(guideModule.gettitle());
        MyApplication myApplication = (MyApplication) getApplicationContext();
        myApplication.AlterTextSize(getWindow().getDecorView());
    }

    @Override
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
        try {
            adManager.Destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }
}