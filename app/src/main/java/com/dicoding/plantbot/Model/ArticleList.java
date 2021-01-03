package com.dicoding.plantbot.Model;

public class ArticleList {
    public String articleName;
    public String imageUrl;



    public ArticleList(String articleName, String imageUrl) {
        this.articleName = articleName;
        this.imageUrl = imageUrl;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
