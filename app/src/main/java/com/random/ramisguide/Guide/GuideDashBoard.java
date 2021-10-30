package com.random.ramisguide.Guide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ironsource.mediationsdk.IronSource;
import com.random.ramisguide.Adapters.GuideAdapter;

import com.random.ramisguide.MyApplication;
import com.random.ramisguide.R;

public class GuideDashBoard extends AppCompatActivity {
    MyApplication adManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_dash_board);
        initRecycler();
        adManager = (MyApplication) getApplicationContext();
        adManager.initAds(this);
        adManager.ShowBanner(findViewById(R.id.banner));
        findViewById(R.id.goback).setOnClickListener(v -> finish());

    }

    private void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        GuideAdapter apps = new GuideAdapter(adManager);
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