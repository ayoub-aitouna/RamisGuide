package com.random.ramisguide.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.random.ramisguide.Modules.WallapaperModule;
import com.random.ramisguide.MyApplication;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.R;
import com.random.ramisguide.Wallapper.WallpaperView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.Holder> {
    ArrayList<WallapaperModule> wallapapers;
    Context context;
    MyApplication app;

    public WallpaperAdapter(MyApplication app) {
        this.app = app;
        this.wallapapers = Config.controls.getWallpapers();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpaper_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        try {
            Picasso.get().load(wallapapers.get(position).getUrl()).into(holder.img);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(v -> {
            Intent view = new Intent(context, WallpaperView.class);
            view.putExtra("Url", wallapapers.get(position).getUrl());
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    app.ShowInter(() -> context.startActivity(view));

                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return wallapapers.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView img;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);

        }
    }
}
