package com.random.ramisguide.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.random.ramisguide.Guide.GuideView;
import com.random.ramisguide.Modules.GuideModule;
import com.random.ramisguide.MyApplication;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GuideAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<GuideModule> guide;
    Context context;
    MyApplication app;

    public GuideAdapter(MyApplication app) {
        this.app = app;
        this.guide = Config.controls.getGuide();
        Log.d("LoadJson", "GuideAdapter: " + guide.size());
    }

    @Override
    public int getItemViewType(int position) {
        if (position != 0 && position % 3 == 0) return 0;
        return 1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        if (viewType == 1)
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_item, parent, false));
        return new NativeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.native_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
            Holder mHolder = (Holder) holder;
            try {
                Picasso.get().load(guide.get(position).getImg()).into(mHolder.img);
                ((Holder) holder).title.setText(guide.get(position).gettitle());
            } catch (Exception e) {
                e.printStackTrace();
            }

            holder.itemView.setOnClickListener(v -> {
                Intent view = new Intent(context, GuideView.class);
                view.putExtra("position", position);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        app.ShowInter(() -> context.startActivity(view));
                    }
                });

            });
        } else {
            NativeHolder nativeHolder = (NativeHolder) holder;
            app.ShowNative(nativeHolder.nativeConatiner);
        }

    }

    @Override
    public int getItemCount() {
        return guide.size() + guide.size() % 3 - 1;
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);

        }
    }

    public static class NativeHolder extends RecyclerView.ViewHolder {
        FrameLayout nativeConatiner;

        public NativeHolder(@NonNull View itemView) {
            super(itemView);
            nativeConatiner = itemView.findViewById(R.id.nativeConatiner);

        }
    }
}
