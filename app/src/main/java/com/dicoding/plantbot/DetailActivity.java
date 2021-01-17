package com.dicoding.plantbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.dicoding.plantbot.Species.EXTRA_COMMON;
import static com.dicoding.plantbot.Species.EXTRA_FAMILY;
import static com.dicoding.plantbot.Species.EXTRA_GENUS;
import static com.dicoding.plantbot.Species.EXTRA_SCIENTIFIC;
import static com.dicoding.plantbot.Species.EXTRA_URL;
import static com.dicoding.plantbot.Species.EXTRA_YEAR;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_COMMON2 = "commonName";
    public static final String EXTRA_URL2 = "imageUrll";
    public String commonName2;
    public String imageUrl2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String commonName = intent.getStringExtra(EXTRA_COMMON);
        commonName2 = commonName;
        imageUrl2 = imageUrl;
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

        Button btnMoveActivity = findViewById(R.id.tambahkan);
        btnMoveActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tambahkan:
                Intent intent3 = new Intent(DetailActivity.this, Jadwal.class);
                intent3.putExtra(EXTRA_COMMON2, commonName2);
                intent3.putExtra(EXTRA_URL2, imageUrl2);
                startActivity(intent3);
                break;
        }
    }
}