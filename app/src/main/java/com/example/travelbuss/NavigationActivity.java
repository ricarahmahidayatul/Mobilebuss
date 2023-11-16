package com.example.travelbuss;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity {
TextView nama;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation);


        frameLayout = findViewById(R.id.konten);

        NavigationActivity navigationActivity1= new NavigationActivity();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction= fragmentManager.beginTransaction();
        transaction.replace(R.id.konten, new BerandaFragment());
        transaction.commit();

        BottomNavigationView bottomNavigationView;
        bottomNavigationView =  findViewById(R.id.bottomnavigasii);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.Beranda){
                Log.d("Beranda", "onNavigationItemSelected: yes");
                FragmentManager fragmentManager1 =getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.konten, new BerandaFragment());
                transaction1.commit();
                return true;
            }else if(item.getItemId() == R.id.lonceng){
                NavigationActivity navigationActivity11 = new NavigationActivity();
                FragmentManager fragmentManager1 =getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.konten, new RiwayatFragment());
                transaction1.commit();
            }else if(item.getItemId() == R.id.akun){
                FragmentManager fragmentManager1 =getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.konten, new AkunFragment());
                transaction1.commit();
            }
            return true;
        });
    }
}