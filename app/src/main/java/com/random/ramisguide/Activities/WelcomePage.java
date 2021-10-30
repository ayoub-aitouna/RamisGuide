package com.random.ramisguide.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ironsource.mediationsdk.IronSource;
import com.random.ramisguide.MyApplication;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.RewardCall;
import com.random.ramisguide.R;

public class WelcomePage extends AppCompatActivity {
    MyApplication adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        adManager = (MyApplication) getApplicationContext();
        adManager.initAds(this);
        adManager.ShowNative(findViewById(R.id.native_ad));
        adManager.ShowBanner(findViewById(R.id.banner));
        findViewById(R.id.welcome).setOnClickListener(v -> {
            adManager.ShowReward(new RewardCall() {
                @Override
                public void call(String type, int amount) {
                    startActivity(new Intent(WelcomePage.this, Settings.class));
                    finish();
                }

                @Override
                public void error() {
                    startActivity(new Intent(WelcomePage.this, Settings.class));
                    finish();
                }
            });
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