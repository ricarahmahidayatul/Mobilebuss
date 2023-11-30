package com.example.travelbuss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class syaratketentuanActivity extends AppCompatActivity {
    ImageButton balek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syaratketentuan);
        balek = findViewById(R.id.back);

        balek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(syaratketentuanActivity.this, AkunFragment.class);
                finish();
            }
        });
    }
}