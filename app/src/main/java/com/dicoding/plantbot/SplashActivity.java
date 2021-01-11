    package com.dicoding.plantbot;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.dicoding.plantbot.Fragment.AddPhotosFragment;

    public class SplashActivity extends FragmentActivity {

    private static int SPLASH_SCREEN = 5000;
    ImageView imgLogo,fontLogo,splashImg;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgLogo = findViewById(R.id.img_logo);
        fontLogo = findViewById(R.id.font_logo);
        splashImg = findViewById(R.id.bg);
        lottieAnimationView = findViewById(R.id.lottie_layer_name);
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this.getApplicationContext(), LoginActivity.class);
                SplashActivity.this.startActivity(intent);
            }
        },SPLASH_SCREEN);

        splashImg.animate().translationY(-3000).setDuration(1000).setStartDelay(4000);
        imgLogo.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        fontLogo.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
    }
}