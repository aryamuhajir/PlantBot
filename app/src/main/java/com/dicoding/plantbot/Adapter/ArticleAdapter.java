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

import com.bumptech.glide.Glide;
import com.dicoding.plantbot.ArticleDetailActivity;
import com.dicoding.plantbot.ArticleListActivity;
import com.dicoding.plantbot.Model.ArticleList;
import com.dicoding.plantbot.R;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private static final String Tag = "RecyclerView";
    private List<ArticleList>articleLists;
    private Context ct;

    public ArticleAdapter(Context ct, List<ArticleList> articleLists) {
        this.articleLists = articleLists;
        this.ct = ct;
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, final int position) {
        holder.articlename.setText(articleLists.get(position).getArticleName());

        Glide.with(holder.itemView.getContext())
                .load(articleLists.get(position).getImageUrl())
                .into(holder.articleimg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ct.getApplicationContext(), ArticleDetailActivity.class);
                intent.putExtra("articleName", articleLists.get(position).getArticleName());
                intent.putExtra("imageUrl", articleLists.get(position).getImageUrl());
                intent.putExtra("newsUrl", articleLists.get(position).getNewsUrl());
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return articleLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView articleimg;
        TextView articlename;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleimg=itemView.findViewById(R.id.article_img);
            articlename=itemView.findViewById(R.id.article_name);
        }
    }
}
