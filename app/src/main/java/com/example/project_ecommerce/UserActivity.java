package com.example.project_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;

public class UserActivity extends AppCompatActivity {
    Button buttonUserLogout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        auth = FirebaseAuth.getInstance();
        buttonUserLogout = (Button) findViewById(R.id.buttonUserLogout);

        buttonUserLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Toast.makeText(UserActivity.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(UserActivity.this,LoginActivity.class));
            }
        });
    }
}