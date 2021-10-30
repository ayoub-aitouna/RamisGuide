package com.random.ramisguide.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.random.ramisguide.MyApplication;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.NetworkAdsManager.Ads.Callback.JsonLoaded;
import com.random.ramisguide.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MyApplication application = (MyApplication) getApplicationContext();
        application.LoadJson(new JsonLoaded() {
            @Override
            public void loaded() {
                if (Config.controls.getSuspended()) {
                    startActivity(new Intent(Splash.this, OnSuspanded.class));
                } else {
                    startActivity(new Intent(Splash.this, MainActivity.class));
                }
                finish();
            }

            @Override
            public void Error() {
                recreate();
            }
        });
    }

    private void EditTheme() {
        try {
            SharedPreferences settings =
                    getSharedPreferences("com.example.YourAppPackage", Context.MODE_PRIVATE);
            String fontSizePref = settings.getString("FONT_SIZE", "Medium");
            int themeID = R.style.FontSizeMedium;
            if (fontSizePref.equals("Small")) {
                themeID = R.style.FontSizeSmall;
            } else if (fontSizePref.equals("Large")) {
                themeID = R.style.FontSizeLarge;
            }
            // Set the theme for the activity.
            setTheme(themeID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}