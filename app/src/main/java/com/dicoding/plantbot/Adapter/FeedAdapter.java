package com.dicoding.plantbot.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.plantbot.FeedActivity;
import com.dicoding.plantbot.Model.AddPhotosModel;
import com.dicoding.plantbot.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private static final String Tag = "RecyclerView";
    private Context mcontext;
    private List<AddPhotosModel>maddPhotosModels;

    public FeedAdapter(Context mcontext, List<AddPhotosModel> maddPhotosModels){
        this.mcontext = mcontext;
        this.maddPhotosModels = maddPhotosModels;
    }

    @NonNull
    @Override
    public FeedAdapter.FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_photo_design,parent,false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, final int position) {
        AddPhotosModel uploadCurrent = maddPhotosModels.get(position);
        holder.nama.setText(uploadCurrent.getName());
        Picasso.get()
                .load(uploadCurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.img);

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcontext.getApplicationContext(), FeedActivity.class);
                intent.putExtra("Name", maddPhotosModels.get( position ).getName());
                intent.putExtra("imageUrl", maddPhotosModels.get( position ).getImageUrl());
                view.getContext().startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount() {

        return maddPhotosModels.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder{
        public TextView nama;
        public ImageView img;

        public FeedViewHolder(View itemView){
            super(itemView);

            nama = itemView.findViewById(R.id.name_feed);
            img = itemView.findViewById(R.id.img_feed);
        }

    }

}
