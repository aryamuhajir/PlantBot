package com.dicoding.plantbot.Model;

public class ArticleList  {

    String articleName;
    String imageUrl;

    public ArticleList(){

    }

    public ArticleList(String articleName, String imageUrl) {
        this.articleName = articleName;
        this.imageUrl = imageUrl;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
