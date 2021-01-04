package com.dicoding.plantbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dicoding.plantbot.Adapter.ArticleAdapter;
import com.dicoding.plantbot.Model.ArticleList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArticleListActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ArticleAdapter articleAdapter;
    private List<ArticleList>articleLists;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        rv=findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        rv.setLayoutManager(layoutManager);
        articleLists=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Articles");
        getImageData();

    }

    private void getImageData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot di:dataSnapshot.getChildren()){
                    ArticleList articleList =new ArticleList();

                    articleList.setImageUrl( di.child( "imageUrl" ).getValue().toString() );
                    articleList.setArticleName( di.child( "articleName" ) .getValue().toString());
                    articleLists.add(articleList);
                }
                articleAdapter = new ArticleAdapter(getApplicationContext(), articleLists);
                rv.setAdapter(articleAdapter);
                articleAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}