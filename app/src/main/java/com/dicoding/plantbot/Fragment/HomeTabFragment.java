package com.dicoding.plantbot.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.plantbot.Adapter.PlantTypeAdapter;
import com.dicoding.plantbot.Model.PlantTypeModel;
import com.dicoding.plantbot.R;
import com.dicoding.plantbot.Sayur;
import com.dicoding.plantbot.Species;

import java.util.ArrayList;

public class HomeTabFragment extends Fragment implements View.OnClickListener {

    Context mContext;
    RecyclerView planttypeRecyclerview;

    ArrayList<PlantTypeModel>plantTypeModels;
    PlantTypeAdapter plantTypeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mContext = this.getActivity();


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout btnMoveActivity = view.findViewById(R.id.spesies);
        btnMoveActivity.setOnClickListener(this);
        CardView btnMoveActivity2 = view.findViewById(R.id.sayur);
        btnMoveActivity2.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sayur:
                Intent intent2 = new Intent(getActivity(), Sayur.class);
                startActivity(intent2);
                break;
            case R.id.spesies:
                Intent intent = new Intent(getActivity(), Species.class);
                startActivity(intent);
                break;


        }

    }
}