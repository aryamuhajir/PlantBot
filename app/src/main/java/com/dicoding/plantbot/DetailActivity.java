package com.dicoding.plantbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.dicoding.plantbot.Species.EXTRA_COMMON;
import static com.dicoding.plantbot.Species.EXTRA_FAMILY;
import static com.dicoding.plantbot.Species.EXTRA_GENUS;
import static com.dicoding.plantbot.Species.EXTRA_SCIENTIFIC;
import static com.dicoding.plantbot.Species.EXTRA_URL;
import static com.dicoding.plantbot.Species.EXTRA_YEAR;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String commonName = intent.getStringExtra(EXTRA_COMMON);
        String familyCommonName = intent.getStringExtra(EXTRA_FAMILY);
        String genus = intent.getStringExtra(EXTRA_GENUS);
        Integer year = intent.getIntExtra(EXTRA_YEAR, 0);
        String scientificName = intent.getStringExtra(EXTRA_SCIENTIFIC);
        ImageView imageViewm = findViewById(R.id.image_view_detail2);
        TextView textCommon = findViewById(R.id.common_name);
        TextView textfamilyCommon = findViewById(R.id.family_name);
        TextView textGenus = findViewById(R.id.genus_name);
        TextView textYear = findViewById(R.id.year2);
        TextView textScientific = findViewById(R.id.scientific_name);
        System.out.println(imageUrl);

        Glide.with(this)
                .load(imageUrl)
                .apply(new RequestOptions())
                .into(imageViewm);

        textCommon.setText(commonName);
        textfamilyCommon.setText(familyCommonName);
        textGenus.setText(genus);
        textYear.setText(Integer.toString(year));
        textScientific.setText(scientificName);


    }
}