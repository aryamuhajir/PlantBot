package com.dicoding.plantbot.Model;

public class PlantTypeModel {
    private int img;
    private String title;

    public PlantTypeModel(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {

        return img;
    }

    public String getTitle() {

        return title;
    }
}
