package com.dicoding.plantbot.Model;

public class plants {
    private String image_url;
    private String common_name;
    private String family_common_name;
    private String genus;
    private Integer year;
    private String scientific_name;


    public String getScientific_name() {
        return scientific_name;
    }


    public String getGenus() {
        return genus;
    }

    public int getYear() {
        return year;
    }

    public plants(String imageUrl, String creator, String likes, String gen, Integer ye, String scien) {
        image_url = imageUrl;
        common_name = creator;
        family_common_name = likes;
        genus = gen;
        year = ye;
        scientific_name = scien;

    }

    public String getImage_url() {
        return image_url;
    }

    public String getCommon_name() {
        return common_name;
    }

    public String getFamily_common_name() {
        return family_common_name;
    }
}
