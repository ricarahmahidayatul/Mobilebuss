package com.example.travelbuss;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class rincian_booking extends AppCompatActivity {

    private EditText tujuan, tglpnjm, tglkmbli, mobil, bbm, total;
    Button bayar;
    FirebaseAuth Auth;
    Long jumlahHari;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_booking);
        bayar = findViewById(R.id.btnbayar);
        tujuan = findViewById(R.id.txtTujuan);
        tglpnjm = findViewById(R.id.txtTglPinjam);
        tglkmbli = findViewById(R.id.txtTglKembali);
        mobil = findViewById(R.id.txtmobil);
        bbm = findViewById(R.id.txtbensin);
        total = findViewById(R.id.txttotal);

        Auth = FirebaseAuth.getInstance();


        FirebaseFirestore db = FirebaseFirestore.getInstance();


        Query reff = db.collection("Booking").whereEqualTo("UID",getIntent().getStringExtra("UID"));

        bayar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String jmlh = total.getText().toString().trim();

                String bookingUid = getIntent().getStringExtra("DocumentID");

                // Membuat referensi ke dokumen "Booking" menggunakan UID
                DocumentReference bookingRef = FirebaseFirestore.getInstance().collection("Booking").document(bookingUid);


                // Mengupdate field "Total" pada dokumen "Booking" dengan nilai yang diambil dari EditText
                bookingRef
                        .update("Total", jmlh)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Tambahkan tindakan yang ingin Anda lakukan setelah berhasil menyimpan total
                                // Contoh: Menampilkan pesan sukses atau pindah ke halaman lain
                                Toast.makeText(rincian_booking.this, "Total berhasil disimpan", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Tambahkan tindakan yang ingin Anda lakukan jika penyimpanan gagal
                                Log.e("TAG", "Gagal menyimpan total", e);
                                Toast.makeText(rincian_booking.this, "Gagal menyimpan total", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        reff.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                QuerySnapshot documentSnapshots = task.getResult();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Log.d("TAG", "Document Id: " + document.getId());
                    jumlahHari = document.getLong("JumlahHari");
                }
                db.collection("Data_Mobil").document(getIntent().getStringExtra("IDMobil")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        mobil.setText( task.getResult().getString("Nama"));
                        Long harga = Long.valueOf(task.getResult().getString("Harga"));
                        Long totalHarga = jumlahHari * harga;
                        total.setText(totalHarga.toString());


                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }

        });

        DocumentReference dbReff = db.collection("Booking").document(Auth.getCurrentUser().getUid());
                dbReff.get().addOnSuccessListener(documentSnapshot -> {

                    String Tuju = documentSnapshot.getString("Tujuan");
                    String TglP = documentSnapshot.getString("TanggalPinjam");
                    String TglK = documentSnapshot.getString("TanggalKembali");
                    String Mob = documentSnapshot.getString("NamaMobil");
                    String Bb = documentSnapshot.getString("BahanBakar");
//                String ttl =documentSnapshot.getString("JumlahHari");
//                String Total = documentSnapshot.getString("Telepon");

                    Log.d("dataprofile", "Tujuan" + Tuju);
                    Log.d("dataprofile", "tglpinjam" + TglP);
                    Log.d("dataprofile", "tglk" + TglK);
                    Log.d("dataprofile", "mobil" + Mob);
                    Log.d("dataprofile", "bbm" + Bb);

//                Log.d("dataprofile", "total" +ttl);

                    tujuan.setText(Tuju);
                    tglpnjm.setText(TglP);
                    tglkmbli.setText(TglK);
                    mobil.setText(Mob);
                    bbm.setText(Bb);
//                total.setText(ttl);
                    tujuan.setText(getIntent().getStringExtra("Tujuan"));
                    tglpnjm.setText(getIntent().getStringExtra("TanggalPinjam"));
                    tglkmbli.setText(getIntent().getStringExtra("TanggalKembali"));
                    mobil.setText(getIntent().getStringExtra("NamaMobil"));
                    bbm.setText(getIntent().getStringExtra("BahanBakar"));
//                total.setText(getIntent().getStringExtra("JumlahHari"));

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });


//        bayar.setOnClickListener(view -> {
//            // Get the UID from the Intent (you may need to modify this based on how you pass data)
//            String bookingUid = getIntent().getStringExtra("UID");
//
//            // Get the total value from the EditText
//            String totalValue = total.getText().toString();
//
//            // Create a reference to the "Booking" document using the UID
//            DocumentReference bookingRef = db.collection("Booking").document(bookingUid);
//
//            // Update the "total" field in the "Booking" document
//            bookingRef
//                    .set("Total", totalValue.addOnSuccessListener);
//        });




            }
        }
