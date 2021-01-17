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
import com.dicoding.plantbot.Adapter.plantsAdapter;
import com.dicoding.plantbot.Model.plants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Species extends AppCompatActivity implements plantsAdapter.OnItemClickListener {

    public static final String EXTRA_URL = "imageUrll";
    public static final String EXTRA_COMMON = "commonName";
    public static final String EXTRA_FAMILY = "familyName";
    public static final String EXTRA_GENUS = "genus";
    public static final String EXTRA_YEAR = "year";
    public static final String EXTRA_SCIENTIFIC = "scientificName";

    private RecyclerView mRecyclerView;
    private plantsAdapter mExampleAdapter;
    private ArrayList<plants> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }


    private void parseJSON() {
        String url = "https://trefle.io/api/v1/plants?token=sXzUMEmU8A2XwBFbbudUmBW3NzRGhC5GlYSl0J2dm5o&filter_not[image_url]=null";
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
                                mExampleList.add(new plants(imageUrl, commonName, familyName, genus, year, scientificName));
                            }
                            mExampleAdapter = new plantsAdapter(Species.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mExampleAdapter.setOnItemClickListener(Species.this);
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
        plants clickedItem = mExampleList.get(position);
        detailIntent.putExtra(EXTRA_URL, clickedItem.getImage_url());
        detailIntent.putExtra(EXTRA_COMMON, clickedItem.getCommon_name());
        detailIntent.putExtra(EXTRA_FAMILY, clickedItem.getFamily_common_name());
        detailIntent.putExtra(EXTRA_GENUS, clickedItem.getGenus());
        detailIntent.putExtra(EXTRA_YEAR, clickedItem.getYear());
        detailIntent.putExtra(EXTRA_SCIENTIFIC, clickedItem.getScientific_name());

        startActivity(detailIntent);

    }
}