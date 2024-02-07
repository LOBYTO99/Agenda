package com.example.lugaresfavoritos.Splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.lugaresfavoritos.R;
import com.example.lugaresfavoritos.Ventanas.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private final  int duracion_splash = 2500;
    ImageView LOGO;
    int resourceID = R.raw.loading;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        LOGO = findViewById(R.id.logo_splash);

        Glide.with(this).asGif().load(resourceID).into(LOGO);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            };
        },duracion_splash);
    }
}