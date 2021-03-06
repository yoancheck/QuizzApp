package com.example.project_java_natan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hotmail.or_dvir.easysettings.pojos.EasySettings;

import java.util.ArrayList;

public class RecyclerViewAdapterColor extends RecyclerView.Adapter<RecyclerViewAdapterColor.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImages;
    private ArrayList<String> mNames;
    private final Context mcontext;

    public RecyclerViewAdapterColor(ArrayList<String> mImages, ArrayList<String> mNames, Context mcontext) {
        this.mImages = mImages;
        this.mNames = mNames;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.color, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: Images Set");
        Glide.with(mcontext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.img);
        holder.txt.setText(mNames.get(position));

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, mNames.get(position), Toast.LENGTH_SHORT).show();
                EasySettings.retrieveSettingsSharedPrefs(mcontext)
                        .edit()
                        .putString("theme", mNames.get(position))
                        .apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView img;
        final TextView txt;
        public View parent_layout;
        //final RelativeLayout parent_layout;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            txt = itemView.findViewById(R.id.text);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}