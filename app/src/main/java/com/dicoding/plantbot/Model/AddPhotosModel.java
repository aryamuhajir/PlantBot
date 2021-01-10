package com.dicoding.plantbot.Model;

public class AddPhotosModel {
    String name;
    String imageUrl;

    public AddPhotosModel(){
    //Empty constructor needed
    }

    public AddPhotosModel(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {

        this.imageUrl = imageUrl;
    }
}
