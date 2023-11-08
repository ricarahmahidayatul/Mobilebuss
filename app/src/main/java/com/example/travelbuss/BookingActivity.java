package com.example.travelbuss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {


    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private Button btnsimpan;
    private EditText editPenjemputan, editTujuan, editTanggalpinjam, editTanggalkembali, editNamamobil, editBahanbakar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        editPenjemputan = findViewById(R.id.txtPenjemputan);
        editTujuan= findViewById(R.id.txtTujuan);
        editTanggalpinjam= findViewById(R.id.txtTglPinjam);
        editTanggalkembali= findViewById(R.id.txtTglKembali);


        btnsimpan = findViewById(R.id.btnpesan);

        db = FirebaseFirestore.getInstance();

        btnsimpan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String penjemputan = editPenjemputan.getText().toString().trim();
                String tujuan = editTujuan.getText().toString().trim();
                String tglpinjam = editTanggalpinjam.getText().toString().trim();
                String tglkembali= editTanggalkembali.getText().toString().trim();
                String mobil = "Avanza";
                String bbm = "iya";

                if(penjemputan.isEmpty()){
                    editPenjemputan.setError("penjemputan tidak boleh kosong");
                } else if (tujuan.isEmpty()) {
                    editTujuan.setError("tujuan tidak boleh kosong");
                } else if (tglpinjam.isEmpty()) {
                    editTanggalpinjam.setError("pilih tanggal pinjam");
                } else if (tglkembali.isEmpty()) {
                    editTanggalkembali.setError("pilih tanggal pinjam");
                } else if (mobil.isEmpty()) {
                    editNamamobil.setError("pilih mobil yang diinginkan");
                } else if (bbm.isEmpty()) {
                    editBahanbakar.setError("pilih bbm");
                }else{
                    Map<String, Object> user = new HashMap<>();
                    user.put("Penjemputan", penjemputan);
                    user.put("Tujuan", tujuan);
                    user.put("Tanggal Pinjam", tglpinjam);
                    user.put("Tanggal Kembali", tglkembali);
                    user.put("Nama Mobil", mobil);
                    user.put("Bahan Bakar", bbm);




                    DocumentReference dbReff = db.collection("Booking").document();
                    dbReff.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    });
                }
            }
        });



    }
}