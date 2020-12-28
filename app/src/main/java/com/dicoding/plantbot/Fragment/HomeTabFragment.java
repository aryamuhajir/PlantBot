package com.dicoding.plantbot.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.plantbot.Adapter.PlantTypeAdapter;
import com.dicoding.plantbot.Model.PlantTypeModel;
import com.dicoding.plantbot.R;

import java.util.ArrayList;

public class HomeTabFragment extends Fragment {

    Context mContext;
    RecyclerView planttypeRecyclerview;

    ArrayList<PlantTypeModel>plantTypeModels;
    PlantTypeAdapter plantTypeAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mContext = this.getActivity();

        planttype_recyclerview();

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void planttype_recyclerview() {
        // Assign Variable
        planttypeRecyclerview = getActivity().findViewById(R.id.plantType_recyclerview);

        //Create Integer Array
        Integer[] img = {R.drawable.buah, R.drawable.bunga, R.drawable.sayur, R.drawable.herbal};

        //Create String Array
        String[] title = {"Buah-buahan", "Bunga", "Sayur-sayuran", "Herbal Keluarga"};

        plantTypeModels = new ArrayList<>();
        for (int i=0;i<img.length; i++) {
            PlantTypeModel plantTypeModel = new PlantTypeModel(img[i],title[i]);
            plantTypeModels.add(plantTypeModel);
        }

        // horizontal layout desain
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                HomeTabFragment.this.getActivity(), LinearLayoutManager.HORIZONTAL, false
        );
        planttypeRecyclerview.setLayoutManager(layoutManager);
        planttypeRecyclerview.setItemAnimator(new DefaultItemAnimator());

        //Initialize PlantTypeAdapter
        plantTypeAdapter = new PlantTypeAdapter(HomeTabFragment.this.getActivity(), plantTypeModels);
        //Set IngredientsAdapter to RecyclerView
        planttypeRecyclerview.setAdapter(plantTypeAdapter);
    }
}