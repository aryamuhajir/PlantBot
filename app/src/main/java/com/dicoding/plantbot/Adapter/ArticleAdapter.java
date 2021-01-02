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
import com.dicoding.plantbot.Model.ArticleList;
import com.dicoding.plantbot.R;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    List<ArticleList>articleLists;
    Context ct;

    public ArticleAdapter(List<ArticleList> articleLists, Context ct) {
        this.articleLists = articleLists;
        this.ct = ct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_articles_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticleList articleList=articleLists.get(position);
        Glide.with(ct)
                .load(articleList.getImageUrl())
                .into(holder.articleimg);

        holder.articlename.setText(articleList.getArticleName());

    }

    @Override
    public int getItemCount() {
        return articleLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView articleimg;
        TextView articlename;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            articleimg=itemView.findViewById(R.id.article_image);
            articlename=itemView.findViewById(R.id.article_name);
        }
    }
}
