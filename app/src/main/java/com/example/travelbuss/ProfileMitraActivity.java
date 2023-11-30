package com.example.travelbuss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class ProfileMitraActivity extends AppCompatActivity {
    ImageButton balek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_mitra);
        balek = findViewById(R.id.back);

        balek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(ProfileMitraActivity.this, AkunFragment.class);
                finish();

            }


        });
    }
}