package com.dicoding.plantbot.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.dicoding.plantbot.Adapter.ProfileAdapter;
import com.dicoding.plantbot.R;
import com.google.android.material.tabs.TabLayout;

public class ProfileTabFragment extends Fragment {

    View myFragment;

    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myFragment = inflater.inflate(R.layout.fragment_profile, container, false);

        viewPager = myFragment.findViewById(R.id.view_pager_profile);
        tabLayout = myFragment.findViewById(R.id.tab_layout_profile);

        return myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        ProfileAdapter profileAdapter = new ProfileAdapter(getChildFragmentManager());

        profileAdapter.addFragment(new ScheduleFragment(), "SCHEDULE");
        profileAdapter.addFragment(new FeedFragment(), "FEED");
        profileAdapter.addFragment(new MyPlantsFragment(), "MY PLANT");

        viewPager.setAdapter(profileAdapter);
    }
}
