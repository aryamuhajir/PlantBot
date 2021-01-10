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
import androidx.fragment.app.Fragment;
import com.dicoding.plantbot.R;

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


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.schedule:
                Intent intent1 = getContext().getPackageManager().getLaunchIntentForPackage("com.e_carter.userroles");
                if (intent1 == null) {
                    intent1 = new Intent(Intent.ACTION_VIEW);
                    intent1.setData(Uri.parse("market://details?id=" + "com.google.ar.lens"));
                }
                intent1.putExtra(Intent.ACTION_SEND , Uri.parse("https://bs.floristic.org/image/o/7210dfc0c2a48bdde194bef37c7b7956974bc1b7"));
                getContext().startActivity(intent1);
                break;
        }
    }
}
