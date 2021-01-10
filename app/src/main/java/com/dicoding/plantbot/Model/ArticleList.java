package com.dicoding.plantbot.Model;

public class ArticleList  {

    String articleName;
    String imageUrl;
    String newsUrl;

    public ArticleList(){

    }

    public ArticleList(String articleName, String imageUrl, String newsUrl) {
        this.articleName = articleName;
        this.imageUrl = imageUrl;
        this.newsUrl = newsUrl;
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

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}
