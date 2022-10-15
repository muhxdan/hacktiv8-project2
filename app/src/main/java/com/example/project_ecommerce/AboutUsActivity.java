package com.example.project_ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

//    String url = "https://api.whatsapp.com/send?phone="+number;
//    Intent i = new Intent(Intent.ACTION_VIEW);
//    i.setData(Uri.parse(url));
//    startActivity(i);

    public void whatsapp(View view) {
        int viewID = view.getId();
        String number = null;
        switch (viewID){
            case R.id.wa_danu:
                number = "62895325393060";
                break;
            case R.id.wa_lucky:
                number = "6287719857757";
                break;
            case R.id.wa_khalivio:
                number = "6281338442260";
                break;
            default:
                break;
        }
        String url = "https://api.whatsapp.com/send?phone="+number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}