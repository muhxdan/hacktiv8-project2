package com.example.project_ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAddStaff, buttonAddStock, buttonToLogout;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        buttonAddStaff = (Button) findViewById(R.id.buttonToStaff);
        buttonAddStock = (Button) findViewById(R.id.buttonToStock);
        buttonToLogout = (Button) findViewById(R.id.buttonToLogout);

        buttonAddStaff.setOnClickListener(this);
        buttonAddStock.setOnClickListener(this);
        buttonToLogout.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonToStaff:
                Toast.makeText(this, auth.getCurrentUser().getEmail().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonToStock:
                Toast.makeText(this, "Button Stock", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this, StockActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonToLogout:
                auth.signOut();
                Toast.makeText(AdminActivity.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(AdminActivity.this,LoginActivity.class));
                break;
        }
    }
}