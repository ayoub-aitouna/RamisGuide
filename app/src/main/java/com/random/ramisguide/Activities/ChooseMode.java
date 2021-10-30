package com.random.ramisguide.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ironsource.mediationsdk.IronSource;
import com.random.ramisguide.Guide.GuideDashBoard;
import com.random.ramisguide.MyApplication;
import com.random.ramisguide.R;
import com.random.ramisguide.Wallapper.WallpaperGrid;

public class ChooseMode extends AppCompatActivity {
    MyApplication adManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_mode);
        adManager = (MyApplication) getApplicationContext();
        adManager.initAds(this);
        adManager.ShowBanner(findViewById(R.id.banner));
        adManager.ShowNative(findViewById(R.id.native_ad));
        findViewById(R.id.wallpaper).setOnClickListener(v -> {
            adManager.ShowInter(() -> startActivity(new Intent(ChooseMode.this, WallpaperGrid.class)));

        });
        findViewById(R.id.guide).setOnClickListener(v -> {
            adManager.ShowInter(() -> startActivity(new Intent(ChooseMode.this, GuideDashBoard.class)));
        });
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