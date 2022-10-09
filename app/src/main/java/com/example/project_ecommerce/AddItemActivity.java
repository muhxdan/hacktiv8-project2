package com.example.project_ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.DocumentTransform;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity {

    private EditText inputIdItem, inputNameItem, inputQuantityItem, inputPictureItem;
    private Button buttonAddItem;
    private FirebaseFirestore db;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);
        dialogMessage("Loading...", "Add Data Item");
        inputIdItem = (EditText) findViewById(R.id.inputIdItem);
        inputNameItem = (EditText) findViewById(R.id.inputItemName);
        inputQuantityItem = (EditText) findViewById(R.id.inputItemQuantity);
        inputPictureItem = (EditText) findViewById(R.id.inputItemPicture);
        buttonAddItem = (Button) findViewById(R.id.buttonAddItem);

        buttonAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(inputIdItem.getText().toString(), inputNameItem.getText().toString(), inputQuantityItem.getText().toString(), inputPictureItem.getText().toString());
            }
        });
    }

    private void saveData(String id, String name, String quantity, String picture){
        Map<String, Object> item = new HashMap<>();
        item.put("id", id);
        item.put("name", name);
        item.put("quantity", quantity);
        item.put("picture", picture);
        item.put("date", FieldValue.serverTimestamp());

        progressDialog.show();
        db.collection("item")
            .add(item)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
    }

    public void dialogMessage(String title, String message){
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
    }
}