package com.dicoding.plantbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dicoding.plantbot.Adapter.plantsAdapter2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Berbuah extends AppCompatActivity implements plantsAdapter2.OnItemClickListener{

    public static final String EXTRA_URL = "imageUrll";
    public static final String EXTRA_COMMON = "commonName";
    public static final String EXTRA_FAMILY = "familyName";
    public static final String EXTRA_GENUS = "genus";
    public static final String EXTRA_YEAR = "year";
    public static final String EXTRA_SCIENTIFIC = "scientificName";



    private RecyclerView mRecyclerView;
    private plantsAdapter2 mExampleAdapter;
    private ArrayList<plants> mExampleList2;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayur);

        mRecyclerView = findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList2 = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }


    private void parseJSON() {
        String url = "https://trefle.io/api/v1/species?token=sXzUMEmU8A2XwBFbbudUmBW3NzRGhC5GlYSl0J2dm5o&filter[fruit_conspicuous]=true&q=banana";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String commonName = hit.getString("common_name");
                                String imageUrl = hit.getString("image_url");
                                String familyName = hit.getString("family_common_name");
                                String genus  = hit.getString("genus");
                                Integer year = hit.getInt("year");
                                String scientificName = hit.getString("scientific_name");

                                mExampleList2.add(new plants(imageUrl, commonName, familyName, genus, year, scientificName));
                            }
                            mExampleAdapter = new plantsAdapter2(Berbuah.this, mExampleList2);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(Berbuah.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        plants clickedItem = mExampleList2.get(position);
        detailIntent.putExtra(EXTRA_URL, clickedItem.getImage_url());
        detailIntent.putExtra(EXTRA_COMMON, clickedItem.getCommon_name());
        detailIntent.putExtra(EXTRA_FAMILY, clickedItem.getFamily_common_name());
        detailIntent.putExtra(EXTRA_GENUS, clickedItem.getGenus());
        detailIntent.putExtra(EXTRA_YEAR, clickedItem.getYear());
        detailIntent.putExtra(EXTRA_SCIENTIFIC, clickedItem.getScientific_name());




        startActivity(detailIntent);

    }
}