package com.random.ramisguide.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ironsource.mediationsdk.IronSource;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.R;
import com.squareup.picasso.Picasso;

public class OnSuspanded extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_suspanded);
        if (!Config.controls.IsSuspended) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Picasso.get()
                    .load(Config.controls.IconUrl)
                    .into((ImageView) findViewById(R.id.icon));
            ((TextView) findViewById(R.id.title)).setText(Config.controls.title);
            findViewById(R.id.update).setOnClickListener(v -> {
                Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + Config.controls.AppUrl));
                marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET | Intent.FLAG_ACTIVITY_MULTIPLE_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(marketIntent);
            });

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }
}