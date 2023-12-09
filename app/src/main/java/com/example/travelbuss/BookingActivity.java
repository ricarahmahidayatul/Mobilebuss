package com.example.travelbuss;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class BookingActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private Button btnsimpan;

    String IDMobil;

    private TextView editTanggalkembali, editTanggalpinjam;
    private EditText editPenjemputan, editTujuan, editnama, editnamamobil;
    private Spinner spinerr;
    private ImageButton balek;
    private ArrayList<String> arrayMobil;
    private  ArrayAdapter<String> adapter;
    private QuerySnapshot mobiles;
    private ProgressDialog progressDialog;
    String Hp;
    private long totalHari, hri;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        mAuth = FirebaseAuth.getInstance();

        spinerr = findViewById(R.id.spinnerlist);
        editnama= findViewById(R.id.txtNama);
        editPenjemputan = findViewById(R.id.txtPenjemputan);
        editTujuan = findViewById(R.id.txtTujuan);
        editTanggalpinjam = findViewById(R.id.txtTglPinjam);
        editTanggalkembali = findViewById(R.id.txtTglKembali);


        btnsimpan = findViewById(R.id.btnpesan);
        balek = findViewById(R.id.back);

        db = FirebaseFirestore.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference dReff = db.collection("Akunn").document(mAuth.getCurrentUser().getUid());
        dReff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Hp = (documentSnapshot.getString("Nohp"));


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        arrayMobil = new ArrayList<>();

        progressDialog = new ProgressDialog(BookingActivity.this);
        progressDialog.setTitle("Proses");
        progressDialog.setMessage("Sabar Bolooo.....");

        getDataSpin();




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
                Toast.makeText(getApplicationContext(), "" +adapter.getItem(i), Toast.LENGTH_SHORT).show();
                IDMobil = mobiles.getDocuments().get(i).getId();
                Log.e("ID Nama_Mobil", mobiles.getDocuments().get(i).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        getDataSpin();

    }

    private void openDatePicker1(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                editTanggalpinjam.setText(String.valueOf(year)+ "-"+String.valueOf(month + 1)+ "-"+String.valueOf( day));

            }
        }, 2023, 00, 12);
//

        datePickerDialog.show();
    }

    private void openDatePicker2(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                editTanggalkembali.setText(String.valueOf(year)+ "-"+String.valueOf(month +1)+ "-"+String.valueOf(day));



            }
        },2023, 00, 12);
//                new SimpleDateFormat("yyyy-MM-dd"));

        datePickerDialog.show();

    }



    private static String generateAutoId() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();

        // Convert UUID to a string and remove hyphens
        String autoGeneratedId = uuid.toString().replace("-", "");

        return generateAutoId();
    }

    private long calculateTotalDays() {
        String pickUpDateawl = editTanggalpinjam.getText().toString();
        String returnDateakhr = editTanggalkembali.getText().toString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date pickUpDate1 = sdf.parse(pickUpDateawl);
            Date returnDate2 = sdf.parse(returnDateakhr);

            long diffInMilliseconds = returnDate2.getTime() - pickUpDate1.getTime();
            hri = diffInMilliseconds / (24 * 60 * 60 * 1000); // Convert milidetik ke hari
            totalHari = hri + 1;

//            TextView totalDaysTextView = findViewById(R.id.total_days);
//            totalDaysTextView.setText(String.valueOf(totalHari));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return totalHari;
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
                    progressDialog.dismiss();
                }else{
                    Toast.makeText(getApplicationContext(), "data tidak ada", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();

            }
        });


        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


//                IDMobil = mobiles.getDocuments().get(i).getId();

                String Spin = spinerr.getSelectedItem().toString().trim();
//                String sp = String.valueOf(Spin == IDMobil);
                Date cektanggalPeminjaman,cektanggalPengembalian;
                String tglPinjam = editTanggalpinjam.getText().toString().trim();
                String tglKembali = editTanggalkembali.getText().toString().trim();

                try {
                    cektanggalPeminjaman = formatter.parse(tglPinjam);
                    cektanggalPengembalian = formatter.parse(tglKembali);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                db.collection("Booking").whereEqualTo("IDMobil",IDMobil).whereGreaterThanOrEqualTo("TanggalPinjam",cektanggalPeminjaman)
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                int jumlahHari = 0;
                                if (!task.getResult().isEmpty()){
                                    Date tanggalPengembalianDatabase = new Date();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                   tanggalPengembalianDatabase =  document.getDate("TanggalKembali");
                                                    Log.d("TAG", "Document Id: " + document.get("JumlahHari").toString());
                                                    jumlahHari = Integer.parseInt(document.get("JumlahHari").toString());
                                                }
                                    Log.d("TambahTanggal", "onComplete: " + addDays(cektanggalPeminjaman,jumlahHari-1));
                                    if (isMoreThanDays(cektanggalPeminjaman,tanggalPengembalianDatabase,jumlahHari-1)){
                                        Toast.makeText(BookingActivity.this, "Benar", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(BookingActivity.this, "data ada", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BookingActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


//        btnsimpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//
//
////                IDMobil = mobiles.getDocuments().get(i).getId();
//
//                String Spin = spinerr.getSelectedItem().toString().trim();
////                String sp = String.valueOf(Spin == IDMobil);
//                Date cektanggalPeminjaman,cektanggalPengembalian;
//                String tglPinjam = editTanggalpinjam.getText().toString().trim();
//                String tglKembali = editTanggalkembali.getText().toString().trim();
//
//                try {
//                    cektanggalPeminjaman = formatter.parse(tglPinjam);
//                    cektanggalPengembalian = formatter.parse(tglKembali);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//                                // Check if the email exists in the "Adminn" collection
//                db.collection("Booking")
//                        .whereEqualTo("IDMobil", IDMobil)
//                        .whereGreaterThanOrEqualTo("TanggalPinjam", tglPinjam)
//                        .whereGreaterThanOrEqualTo("TanggalKembali", tglKembali)
//                        .get()
//                        .addOnCompleteListener(task -> {
//                            if (!task.getResult().isEmpty()) {
//
//
////                                            db.collection("Booking")
////                                                    .whereEqualTo()
//                                Toast.makeText(getApplicationContext(), "Mobil Tidak Tersedia Pada Tanggal Tersebut", Toast.LENGTH_SHORT).show();
//
//
//                                // Email exists in the "Adminn" collection, proceed with login
//
//
//                            } else {
//                                // Email does not exist in the "Adminn" collection
//
//
//
//                                String penjemputan = editPenjemputan.getText().toString().trim();
//                                String tujuan = editTujuan.getText().toString().trim();
//                                String tglpinjam = editTanggalpinjam.getText().toString().trim();
//                                String tglkembali = editTanggalkembali.getText().toString().trim();
//                                String mobil = spinerr.getSelectedItem().toString();
//                                String nama = editnama.getText().toString().trim();
//                                String nohp = Hp;
//
//
//                                Date tanggalPeminjaman,tanggalPengembalian;
//                                try {
//                                    tanggalPeminjaman = formatter.parse(tglpinjam);
//                                    tanggalPengembalian = formatter.parse(tglkembali);
//                                } catch (ParseException e) {
//                                    throw new RuntimeException(e);
//                                }
//
//
////                String mobilhr = spinerr.getSelectedItem().toString();
//
//                                Log.d("onClick", "onClick: idMobil" + IDMobil);
//                                if (penjemputan.isEmpty()) {
//                                    editPenjemputan.setError("penjemputan tidak boleh kosong");
//                                } else if (tujuan.isEmpty()) {
//                                    editTujuan.setError("tujuan tidak boleh kosong");
//                                } else if (tglpinjam.isEmpty()) {
//                                    editTanggalpinjam.setError("pilih tanggal pinjam");
//                                } else if (tglkembali.isEmpty()) {
//                                    editTanggalkembali.setError("pilih tanggal pinjam");
//                                } else if (mobil.isEmpty()) {
//                                    editnamamobil.setError("pilih mobil yang diinginkan");
//                                } else if (nama.isEmpty()) {
//                                    editnama.setError("masukan nama");
//                                } else {
//
//                                    long hari = calculateTotalDays();
//                                    if (hari <= 0) {
//                                        // Show an error message when total days is less than or equal to 0
//                                        Toast.makeText(getApplicationContext(), "Kesalaahn dalam memilih tanggal", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        // Continue with saving the data
//                                        // ... (existing code)
//                                        Map<String, Object> user = new HashMap<>();
////                        long hari = calculateTotalDays();
//
//                                        user.put("Penjemputan", penjemputan);
//                                        user.put("Tujuan", tujuan);
//                                        user.put("TanggalPinjam", tanggalPeminjaman);
//                                        user.put("TanggalKembali", tanggalPengembalian);
//                                        user.put("IDMobil", IDMobil);
//                                        user.put("NamaPenyewa", nama);
//                                        user.put("JumlahHari", hari);
//                                        user.put("UID", mAuth.getCurrentUser().getUid());
//                                        user.put("NoHp", nohp);
//
//
//
//                                        CollectionReference dbReff = db.collection("Booking");
//
//
//                                        dbReff.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                                            @Override
//                                            public void onSuccess(DocumentReference documentReference) {
//                                                Intent intent = new Intent(BookingActivity.this, rincian_booking.class);
//                                                intent.putExtra("Tujuan", tujuan);
//                                                intent.putExtra("TanggalPinjam", tglpinjam);
//                                                intent.putExtra("TanggalKembali", tglkembali);
//                                                intent.putExtra("IDMobil", IDMobil);
//                                                intent.putExtra("NamaPenyewa", nama);
//                                                intent.putExtra("JumlahHari", hari);
//                                                intent.putExtra("UID", mAuth.getCurrentUser().getUid());
//                                                intent.putExtra("NoHp", nohp);
//                                                intent.putExtra("DocumentID", documentReference.getId());
//
//
//                                                startActivity(intent);
//                                            }
//                                        });
//
//
//                                        db.collection("Data_Mobil").whereEqualTo("Nama", spinerr.getSelectedItem().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                                    Log.d("TAG", "Document Id: " + document.getId());
//                                                }
//                                            }
//                                        });
//
//                                    }
//                                }
//
//
//                            }
//                        });
////                                });
//
//            }
//        });

//        btnsimpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String penjemputan = editPenjemputan.getText().toString().trim();
//                String tujuan = editTujuan.getText().toString().trim();
//                String tglpinjam = editTanggalpinjam.getText().toString().trim();
//                String tglkembali = editTanggalkembali.getText().toString().trim();
//                String mobil = spinerr.getSelectedItem().toString();
//                String nama = editnama.getText().toString().trim();
//                String nohp = Hp;
//
////                String mobilhr = spinerr.getSelectedItem().toString();
//
//                Log.d("onClick", "onClick: idMobil" + IDMobil);
//                if (penjemputan.isEmpty()) {
//                    editPenjemputan.setError("penjemputan tidak boleh kosong");
//                } else if (tujuan.isEmpty()) {
//                    editTujuan.setError("tujuan tidak boleh kosong");
//                } else if (tglpinjam.isEmpty()) {
//                    editTanggalpinjam.setError("pilih tanggal pinjam");
//                } else if (tglkembali.isEmpty()) {
//                    editTanggalkembali.setError("pilih tanggal pinjam");
//                } else if (mobil.isEmpty()) {
//                    editnamamobil.setError("pilih mobil yang diinginkan");
//                } else if (nama.isEmpty()) {
//                    editnama.setError("masukan nama");
//                } else {
//
//                    long hari = calculateTotalDays();
//                    if (hari <= 0) {
//                        // Show an error message when total days is less than or equal to 0
//                        Toast.makeText(getApplicationContext(), "Kesalaahn dalam memilih tanggal", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // Continue with saving the data
//                        // ... (existing code)
//                        Map<String, Object> user = new HashMap<>();
////                        long hari = calculateTotalDays();
//
//                        user.put("Penjemputan", penjemputan);
//                        user.put("Tujuan", tujuan);
//                        user.put("TanggalPinjam", tglpinjam);
//                        user.put("TanggalKembali", tglkembali);
//                        user.put("IDMobil", IDMobil);
//                        user.put("NamaPenyewa", nama);
//                        user.put("JumlahHari", hari);
//                        user.put("NoHp", nohp);
//                        user.put("UID", mAuth.getCurrentUser().getUid());
//
//
//                        CollectionReference dbReff = db.collection("Booking");
//
//
//                        dbReff.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Intent intent = new Intent(BookingActivity.this, rincian_booking.class);
//                                intent.putExtra("Tujuan", tujuan);
//                                intent.putExtra("TanggalPinjam", tglpinjam);
//                                intent.putExtra("TanggalKembali", tglkembali);
//                                intent.putExtra("IDMobil", IDMobil);
//                                intent.putExtra("NamaPenyewa", nama);
//                                intent.putExtra("JumlahHari", hari);
//                                intent.putExtra("NoHp", nohp);
//                                intent.putExtra("UID", mAuth.getCurrentUser().getUid());
//                                intent.putExtra("DocumentID", documentReference.getId());
//
//
//                                startActivity(intent);
//                            }
//                        });
//
//
//                        db.collection("Data_Mobil").whereEqualTo("Nama", spinerr.getSelectedItem().toString()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                    Log.d("TAG", "Document Id: " + document.getId());
//                                }
//                            }
//                        });
//
//                    }
//                }
//            }
//        });



    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static boolean isMoreThanDays(Date tanggalAkanBooking,Date bookingDate, int days) {


        // Hitung selisih hari dalam millisecond
        long diffInMillies = Math.abs(tanggalAkanBooking.getTime() - bookingDate.getTime());

        // Konversi millisecond ke hari
        long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        // Bandingkan selisih hari dengan parameter days
        return diffInDays > days;
    }



}
