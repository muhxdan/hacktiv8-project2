package com.example.project_ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.project_ecommerce.adapter.CategoryAdapter;
import com.example.project_ecommerce.adapter.ItemAdapter;
import com.example.project_ecommerce.model.Item;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductActivity extends AppCompatActivity {
    FirebaseFirestore db;
    List<Item> list = new ArrayList<>();
    private ProgressDialog progressDialog;
    ItemAdapter itemAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        recyclerView = (RecyclerView) findViewById(R.id.rvProduct);
        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Get data...");
        itemAdapter = new ItemAdapter(this, list);




        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    private void getData(){
        Intent intent = getIntent();
        String getFilter = intent.getStringExtra("category");
        Toast.makeText(this, getFilter.toLowerCase(), Toast.LENGTH_SHORT).show();
        progressDialog.show();
        db.collection("item").whereEqualTo("category", getFilter.toLowerCase())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                Item item = new Item(document.getString("id"), document.getString("name"), document.getString("quantity"), document.getString("picture"), document.getString("category"), document.getString("filter"));
                                item.setDocId(document.getId());
                                list.add(item);
                            }
                            itemAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(), "Data gagal di ambil!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}