package com.random.ramisguide.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.random.ramisguide.Adapters.FeatruedAdapter;
import com.random.ramisguide.MyApplication;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.InterCallback;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.R;

public class MainActivity extends AppCompatActivity {

    MyApplication adManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adManager = (MyApplication) getApplicationContext();
        adManager.initAds(this);
        adManager.ShowNative(findViewById(R.id.native_ad));
        adManager.ShowBanner(findViewById(R.id.banner));
        adManager.ShowReview(this);
        initRecycler();
        findViewById(R.id.start).setOnClickListener(v -> {
            adManager.ShowInter(() -> {
                startActivity(new Intent(MainActivity.this, WelcomePage.class));
            });
        });
        findViewById(R.id.privacy).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, privacy.class));
        });
        findViewById(R.id.share).setOnClickListener(v -> {
            ShareCompat.IntentBuilder.from(this)
                    .setType("text/plain")
                    .setChooserTitle("Chooser title")
                    .setText("http://play.google.com/store/apps/details?id=" + this.getPackageName())
                    .startChooser();
        });
    }

    private void initRecycler() {
        if (Config.controls.getFeaturedAppON()) {
            RecyclerView recyclerView = findViewById(R.id.features);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            FeatruedAdapter apps = new FeatruedAdapter();
            recyclerView.setAdapter(apps);
        }
    }
}