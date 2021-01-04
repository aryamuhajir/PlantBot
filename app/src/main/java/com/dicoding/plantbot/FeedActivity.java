package com.dicoding.plantbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.plantbot.Adapter.FeedAdapter;
import com.dicoding.plantbot.Model.AddPhotosModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity{

    private RecyclerView feedRecyclerView;
    private FeedAdapter feedAdapter;

    private ProgressBar feedProgressBar;

    private DatabaseReference databaseReference;
    private List<AddPhotosModel> maddPhotosModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        // horizontal layout desain
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);

        feedRecyclerView = findViewById(R.id.feed_recyclerview);
        feedRecyclerView.setHasFixedSize(true);
        feedRecyclerView.setLayoutManager(layoutManager);

        feedProgressBar = findViewById(R.id.progress_bar_feed);

        maddPhotosModels = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    AddPhotosModel addPhotosModel =new AddPhotosModel();

                    addPhotosModel.setImageUrl(postSnapshot.child("imageUrl").getValue().toString());
                    addPhotosModel.setName(postSnapshot.child("name").getValue().toString());
                    maddPhotosModels.add(addPhotosModel);
                }
                feedAdapter = new FeedAdapter(getApplicationContext(),maddPhotosModels);
                feedRecyclerView.setAdapter(feedAdapter);
                feedAdapter.notifyDataSetChanged();
                feedProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FeedActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                feedProgressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}