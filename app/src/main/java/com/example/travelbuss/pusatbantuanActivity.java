package com.example.travelbuss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class pusatbantuanActivity extends AppCompatActivity {
ImageButton balek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pusatbantuan);
        balek = findViewById(R.id.back);

        balek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(pusatbantuanActivity.this, AkunFragment.class);
                startActivity(inten);
            }
        });
    }
}