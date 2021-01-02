package com.dicoding.plantbot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.plantbot.R;
import com.dicoding.plantbot.plants;

import java.util.ArrayList;

public class plantsAdapter2 extends RecyclerView.Adapter<plantsAdapter2.PlantsViewHolder2> {
    private Context mContext;
    private ArrayList<plants> mExampleList2;
    private OnItemClickListener mListener;

    @NonNull
    @Override
    public PlantsViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_listplants2, parent, false);
        return new PlantsViewHolder2(v);

    }

    @Override
    public void onBindViewHolder(@NonNull PlantsViewHolder2 holder, int position) {



        plants currentItem = mExampleList2.get(position);
        String imageUrl = currentItem.getImage_url();
        String commonName = currentItem.getCommon_name();
        String familyCommon = currentItem.getFamily_common_name();
        String genus = currentItem.getGenus();
        int year = currentItem.getYear();
        String scientificName = currentItem.getScientific_name();
        holder.mCommonName.setText(commonName);
        holder.mFamilyName.setText(familyCommon);

        Glide.with(holder.itemView.getContext())
                .load(currentItem.getImage_url())
                .apply(new RequestOptions())
                .into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mExampleList2.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(plantsAdapter2.OnItemClickListener listener) {
        mListener = listener;
    }

    public plantsAdapter2(Context context, ArrayList<plants> exampleList) {
        mContext = context;
        mExampleList2 = exampleList;
    }


    public class PlantsViewHolder2 extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mCommonName;
        public TextView mFamilyName;
        public TextView mGenus;
        public TextView mYear;
        public TextView mScientificName;

        public PlantsViewHolder2(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mCommonName = itemView.findViewById(R.id.text_view_user_name);
            mFamilyName = itemView.findViewById(R.id.text_view_user_name2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}

