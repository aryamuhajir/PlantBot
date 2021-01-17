package com.dicoding.plantbot.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.dicoding.plantbot.FeedActivity;
import com.dicoding.plantbot.Jadwal;
import com.dicoding.plantbot.MyPlantsActivity;
import com.dicoding.plantbot.R;
import com.dicoding.plantbot.Sayur;
import com.dicoding.plantbot.Species;

public class ProfileTabFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout btnMoveActivity4 = view.findViewById(R.id.schedule);
        btnMoveActivity4.setOnClickListener(this);
        RelativeLayout btnMoveActivity2 = view.findViewById(R.id.feed);
        btnMoveActivity2.setOnClickListener(this);
        RelativeLayout btnMoveActivity3 = view.findViewById(R.id.myplant);
        btnMoveActivity3.setOnClickListener(this);
        CardView btnMoveActivity1 = view.findViewById(R.id.show_photo);
        btnMoveActivity1.setOnClickListener(this);
        CardView btnMoveActivity5 = view.findViewById(R.id.plant_image);
        btnMoveActivity5.setOnClickListener(this);
        CardView btnMoveActivity6 = view.findViewById(R.id.schedule_image);
        btnMoveActivity6.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.schedule:
                Intent intent7 = new Intent(getActivity(), Species.class);
                startActivity(intent7);
                break;
            case R.id.feed:
                Intent intent2 = new Intent(getActivity(), FeedActivity.class);
                startActivity(intent2);
                break;
            case R.id.myplant:
                Intent intent3 = new Intent(getActivity(), MyPlantsActivity.class);
                startActivity(intent3);
                break;
            case R.id.schedule_image:
                Intent intent8 = new Intent(getActivity(), Species.class);
                startActivity(intent8);
                break;
            case R.id.show_photo:
                Intent intent5 = new Intent(getActivity(), FeedActivity.class);
                startActivity(intent5);
                break;
            case R.id.plant_image:
                Intent intent6 = new Intent(getActivity(), Species.class);
                startActivity(intent6);
                break;
        }
    }
}
