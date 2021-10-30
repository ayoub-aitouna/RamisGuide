package com.random.ramisguide.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.random.ramisguide.Modules.FeatrudeModule;
import com.random.ramisguide.Utils.Config;
import com.random.ramisguide.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeatruedAdapter extends RecyclerView.Adapter<FeatruedAdapter.Holder> {
    ArrayList<FeatrudeModule> apps;
    Context context;

    public FeatruedAdapter() {
        this.apps = Config.controls.getApps();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.apps_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Picasso.get().load(apps.get(position).getImg()).into(holder.img);
        holder.name.setText(apps.get(position).getName());
        holder.itemView.setOnClickListener(v -> {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + apps.get(position).getUrl())));
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;

        public Holder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);

        }
    }
}
