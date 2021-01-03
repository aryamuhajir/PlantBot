package com.dicoding.plantbot.Model;

public class AddPhotosModel {
    private String mname;
    private String mimageUrl;

    public AddPhotosModel(){

    }

    public AddPhotosModel(String name, String imageUrl) {
        if (name.trim().equals("")){
            name = "Nama Tidak Diberikan";
        }

        mname = name;
        mimageUrl = imageUrl;
    }

    public String getName() {
        return mname;
    }

    public void setName(String name) {
        this.mname = name;
    }

    public String getImageUrl() {
        return mimageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mimageUrl = imageUrl;
    }
}
