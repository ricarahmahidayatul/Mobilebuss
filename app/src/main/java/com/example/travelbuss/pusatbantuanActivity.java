package com.example.travelbuss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class pusatbantuanActivity extends AppCompatActivity {
ImageButton balek;
Button btnwaa;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pusatbantuan);
        balek = findViewById(R.id.back);
        btnwaa = findViewById(R.id.btnwa);

        btnwaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent watsap = new Intent(Intent.ACTION_VIEW);
                watsap.setData(Uri.parse("https://wa.me/6281230072149?qr=1"));
                startActivity(watsap);
            }
        });



        balek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(pusatbantuanActivity.this, AkunFragment.class);
                startActivity(inten);
            }
        });
    }
}