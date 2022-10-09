package com.example.project_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=1000;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        auth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(auth.getCurrentUser() != null) {
                    String getEmail = auth.getCurrentUser().getEmail().toString();

                    if(getEmail.equals("admin@admin.com") || getEmail.equals("admin1@admin.com")){
                        Intent intent = new Intent(SplashActivity.this, AdminActivity.class);
                        Toast.makeText(SplashActivity.this, "Selamat datang admin", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(SplashActivity.this, UserActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}