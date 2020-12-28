package com.dicoding.plantbot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.dicoding.plantbot.Model.PlantTypeModel;
import com.dicoding.plantbot.R;

import java.util.ArrayList;
import java.util.List;

public class PlantTypeAdapter extends RecyclerView.Adapter<PlantTypeAdapter.ViewHolder> {

    ArrayList<PlantTypeModel> plantTypeModel;
    Context context;

    public PlantTypeAdapter(Context context, ArrayList<PlantTypeModel> plantTypeModel) {
        this.context = context;
        this.plantTypeModel = plantTypeModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plant_type_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Set image to ImageView
        holder.imageView.setImageResource(plantTypeModel.get(position).getImg());
        //Set text to TextView
        holder.textView.setText(plantTypeModel.get(position).getTitle());
    }

    @Override
    public int getItemCount() {

        return plantTypeModel.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            imageView = itemView.findViewById(R.id.img_planttype);
            textView = itemView.findViewById(R.id.title_planttype);
        }
    }
}
