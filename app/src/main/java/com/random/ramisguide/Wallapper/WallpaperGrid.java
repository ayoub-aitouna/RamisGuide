package com.random.ramisguide.Wallapper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ironsource.mediationsdk.IronSource;
import com.random.ramisguide.Adapters.WallpaperAdapter;
import com.random.ramisguide.MyApplication;
import com.random.ramisguide.R;

public class WallpaperGrid extends AppCompatActivity {
    MyApplication adManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper_grid);
        adManager = (MyApplication) getApplicationContext();
        adManager.initAds(this);

        initRecycler();
        adManager.ShowBanner(findViewById(R.id.banner));
        findViewById(R.id.goback).setOnClickListener(v -> {
            finish();
        });
    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        WallpaperAdapter apps = new WallpaperAdapter(adManager);
        recyclerView.setAdapter(apps);
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