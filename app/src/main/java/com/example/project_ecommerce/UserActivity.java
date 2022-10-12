package com.example.project_ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.auth.User;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton buttonUserLogout;
    CardView elec,baju,buku,others;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        auth = FirebaseAuth.getInstance();
        buttonUserLogout = (FloatingActionButton) findViewById(R.id.buttonUserLogout);
        elec = (CardView)  findViewById(R.id.electronic);
        baju = (CardView) findViewById(R.id.clothing);
        buku = (CardView) findViewById(R.id.buku);
        others = (CardView) findViewById(R.id.others);

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

    @Override
    public void onClick(View view) {
        // onclicknya masih gagal
        if(view.getId() == R.id.electronic){
            Intent intent = new Intent(this, ProductActivity.class);
            Toast.makeText(UserActivity.this, "masuk", Toast.LENGTH_SHORT).show();
            //put extra buat dapet kategori

            startActivity(intent);
        }else if(view.getId() == R.id.clothing){
            Intent intent = new Intent(this, ProductActivity.class);
            //put extra buat dapet kategori

            startActivity(intent);
        }else if(view.getId() == R.id.buku){
            Intent intent = new Intent(this, ProductActivity.class);
            //put extra buat dapet kategori

            startActivity(intent);
        }else{
            Intent intent = new Intent(this, ProductActivity.class);
            //put extra buat dapet kategori

            startActivity(intent);
        }
    }
}