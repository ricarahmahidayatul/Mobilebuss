package com.example.travelbuss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {


    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private Button btnsimpan;

    private TextView editTanggalkembali, editTanggalpinjam;
    private EditText editPenjemputan, editTujuan, editNamamobil, editBahanbakar;
    private Spinner spinerr;
    private ImageButton balek;
    private ArrayList<String> arrayMobil;
    private  ArrayAdapter<String> adapter;
    private QuerySnapshot mobiles;
    private ProgressDialog progressDialog;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        spinerr = findViewById(R.id.spinnerlist);
        editPenjemputan = findViewById(R.id.txtPenjemputan);
        editTujuan = findViewById(R.id.txtTujuan);
        editTanggalpinjam = findViewById(R.id.txtTglPinjam);
        editTanggalkembali = findViewById(R.id.txtTglKembali);


        btnsimpan = findViewById(R.id.btnpesan);
        balek = findViewById(R.id.back);

        db = FirebaseFirestore.getInstance();

        arrayMobil = new ArrayList<>();

        progressDialog = new ProgressDialog(BookingActivity.this);
        progressDialog.setTitle("Proses");
        progressDialog.setMessage("Sabar Bolooo.....");


        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayMobil);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerr.setAdapter(adapter);




       balek.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent inten = new Intent(BookingActivity.this, NavigationActivity.class);
               startActivity(inten);
           }
       });
        editTanggalkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDatePicker2();

            }
        });

        editTanggalpinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDatePicker1();

            }
        });



        spinerr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), adapter.getItem(i), Toast.LENGTH_SHORT).show();
                Log.e("ID Nama_Mobil", mobiles.getDocuments().get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getDataSpin();

    }

    private void openDatePicker1(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                editTanggalpinjam.setText(String.valueOf(year)+ "."+String.valueOf(month)+ "."+String.valueOf(day));

            }
        }, 2023, 11, 9);

        datePickerDialog.show();
    }

    private void openDatePicker2(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                editTanggalkembali.setText(String.valueOf(year)+ "."+String.valueOf(month)+ "."+String.valueOf(day));

            }
        }, 2023, 11, 9);

        datePickerDialog.show();
    }



        private void getDataSpin(){
            progressDialog.show();
            db.collection("Data_Mobil").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    progressDialog.show();
                    mobiles = queryDocumentSnapshots;
                    if (queryDocumentSnapshots.size()>0){
                        arrayMobil.clear();
                        for (DocumentSnapshot doc : queryDocumentSnapshots){
                            arrayMobil.add(doc.getString("Nama"));

                        }
                        adapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(getApplicationContext(), "data idak ada", Toast.LENGTH_SHORT).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressDialog.show();
                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                }
            });




        btnsimpan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String penjemputan = editPenjemputan.getText().toString().trim();
                String tujuan = editTujuan.getText().toString().trim();
                String tglpinjam = editTanggalpinjam.getText().toString().trim();
                String tglkembali= editTanggalkembali.getText().toString().trim();
                String mobil = spinerr.getSelectedItem().toString();
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
                    Intent Intent = new Intent(BookingActivity.this, rincianPembayaran.class);
                    startActivity(Intent);
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
