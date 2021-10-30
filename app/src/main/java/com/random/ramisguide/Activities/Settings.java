package com.random.ramisguide.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ironsource.mediationsdk.IronSource;
import com.random.ramisguide.MyApplication;
import com.random.ramisguide.R;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

public class Settings extends AppCompatActivity {
    private String Size = "Medium";
    private String Mode = "Light";
    MyApplication adManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        adManager = (MyApplication) getApplicationContext();
        adManager.initAds(this);
        SharedPreferences settings =
                getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        adManager.ShowNative(findViewById(R.id.native_ad));
        if (settings.getString("FONT_SIZE", null) != null) {
            startActivity(new Intent(Settings.this, ChooseMode.class));
            finish();
        } else {
            if (settings.getString("MODE", null) != null && settings.getString("MODE", null).equals("Light")) {
                RadioButton button = findViewById(R.id.light);
                button.setChecked(true);
            }
            if (settings.getString("MODE", null) != null && settings.getString("MODE", null).equals("Dark")) {
                RadioButton button = findViewById(R.id.dark);
                button.setChecked(true);
            }
            if (settings.getString("FONT_SIZE", null) != null && settings.getString("Medium", null).equals("Medium")) {
                RadioButton button = findViewById(R.id.Small);
                button.setChecked(true);
            }
            if (settings.getString("FONT_SIZE", null) != null && settings.getString("Medium", null).equals("Medium")) {
                RadioButton button = findViewById(R.id.Medium);
                button.setChecked(true);
            }
            if (settings.getString("FONT_SIZE", null) != null && settings.getString("Medium", null).equals("Medium")) {
                RadioButton button = findViewById(R.id.Large);
                button.setChecked(true);
            }
        }


        ((RadioGroup) findViewById(R.id.mode)).setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton checked = findViewById(radioGroup.getCheckedRadioButtonId());
            Mode = checked.getTag().toString();
            AppCompatDelegate.setDefaultNightMode(Mode.equals("Light") ? MODE_NIGHT_NO : MODE_NIGHT_YES);

        });
        ((RadioGroup) findViewById(R.id.size)).setOnCheckedChangeListener((radioGroup, i) -> {
            RadioButton checked = findViewById(radioGroup.getCheckedRadioButtonId());
            Size = checked.getTag().toString();

        });

        findViewById(R.id.next).setOnClickListener(v -> {
            SharedPreferences sharedPref = getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("MODE", Mode);
            editor.putString("FONT_SIZE", Size);
            editor.apply();
            startActivity(new Intent(Settings.this, ChooseMode.class));
            finish();
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