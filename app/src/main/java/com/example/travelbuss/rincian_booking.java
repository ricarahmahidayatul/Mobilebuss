package com.example.travelbuss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.CreditCard;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class rincian_booking extends AppCompatActivity implements TransactionFinishedCallback {

    private EditText tujuan, tglpnjm, tglkmbli, mobil, nama, total, haei;
    Button bayar,cobaBayar;
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
        nama = findViewById(R.id.txtbensin);
        total = findViewById(R.id.txttotal);




        Auth = FirebaseAuth.getInstance();

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d("MainActivity", "handleOnBackPressed");
                deletketikagagal();
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String bookingUid = getIntent().getStringExtra("DocumentID");

        DocumentReference reff = db.collection("Booking").document(bookingUid);

        reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                jumlahHari = documentSnapshot.getLong("JumlahHari");
                db.collection("Data_Mobil").document(getIntent().getStringExtra("IDMobil")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        mobil.setText( task.getResult().getString("Nama"));
                        Long harga = Long.valueOf(task.getResult().getString("Harga"));

                        if (jumlahHari == 1){
                            Long totalHarga = harga;
                            total.setText(totalHarga.toString());
                        }else {
                            Long totalHarga = harga * jumlahHari;
                            total.setText(totalHarga.toString());
                        }




                    }
                });


            }

        });



//        cobaBayar.setOnClickListener(view -> {
//            clickPay();
//        });
        bayar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String jmlh = total.getText().toString().trim();

                clickPay();
                // Membuat referensi ke dokumen "Booking" menggunakan UID
                assert bookingUid != null;
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
                                finish();
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

//        reff.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                QuerySnapshot documentSnapshots = task.getResult();
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    Log.d("TAG", "Document Id: " + document.getId());
//                    jumlahHari = document.getLong("JumlahHari");
//                }
//                db.collection("Data_Mobil").document(getIntent().getStringExtra("IDMobil")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//
//                    @Override
//                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                        mobil.setText( task.getResult().getString("Nama"));
//                        Long harga = Long.valueOf(task.getResult().getString("Harga"));
//
//                        if (jumlahHari == 1){
//                            Long totalHarga = harga;
//                            total.setText(totalHarga.toString());
//                        }else {
//                            Long totalHarga = harga * jumlahHari;
//                            total.setText(totalHarga.toString());
//                        }
//
//
//
//
//                    }
//
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });
//            }
//bookingUid
//        });

        DocumentReference dbReff = db.collection("Booking").document(bookingUid);
                dbReff.get().addOnSuccessListener(documentSnapshot -> {

                    String Tuju = documentSnapshot.getString("Tujuan");


                    Timestamp TglP = documentSnapshot.getTimestamp("TanggalPinjam");
                    Timestamp TglK = documentSnapshot.getTimestamp("TanggalKembali");
                    String Mob = documentSnapshot.getString("NamaMobil");
                    String Nma = documentSnapshot.getString("NamaPenyewa");
//                String ttl =documentSnapshot.getString("JumlahHari");
//                String Total = documentSnapshot.getString("Telepon");

                    Log.d("dataprofile", "Tujuan" + Tuju);
                    Log.d("dataprofile", "tglpinjam" + TglP);
                    Log.d("dataprofile", "tglk" + TglK);
                    Log.d("dataprofile", "mobil" + Mob);
                    Log.d("dataprofile", "Namapenyewa" + Nma);

//                Log.d("dataprofile", "total" +ttl);
                    tglpnjm.setText(formatFirestoreTimestamp(TglP));
                    tglkmbli.setText(formatFirestoreTimestamp(TglK));
                    tujuan.setText(Tuju);
//                    tglpnjm.setText(TglP);

                    mobil.setText(Mob);
                    nama.setText(Nma);
//                total.setText(ttl);
                    tujuan.setText(getIntent().getStringExtra("Tujuan"));
                    tglpnjm.setText(getIntent().getStringExtra("TanggalPinjam"));
                    tglkmbli.setText(getIntent().getStringExtra("TanggalKembali"));
                    mobil.setText(getIntent().getStringExtra("NamaMobil"));
                    nama.setText(getIntent().getStringExtra("NamaPenyewa"));
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



makePayment();
            }

    private void makePayment() {
        SdkUIFlowBuilder.init()
                .setContext(this)
                .setMerchantBaseUrl("http://103.127.98.193/rica/")
                .setClientKey("SB-Mid-client-rsH44pqfaHs9nGC2")
                .setTransactionFinishedCallback(this)
                .enableLog(true)
                .setLanguage("id")
                .setColorTheme(new CustomColorTheme("#00A3FF", "#000000", "#00A3FF"))
                .buildSDK();
    }



    @Override
    public void onTransactionFinished(TransactionResult result) {
        if (result.getResponse() != null) {
            switch (result.getStatus()) {
                case TransactionResult.STATUS_SUCCESS:
                    Toast.makeText(this, "Transaction Berhasiil  " + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, NavigationActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case TransactionResult.STATUS_PENDING:
                    deletketikagagal();
                    Toast.makeText(this, "Transaction gagal Pending " + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();

                    break;
                case TransactionResult.STATUS_FAILED:
                    deletketikagagal();
                    Toast.makeText(this, "Transaction Faileded" + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();

                    break;
            }
            result.getResponse().getStatusMessage();
        } else if (result.isTransactionCanceled()) {
            deletketikagagal();

            Toast.makeText(this, "Transaction gagal", Toast.LENGTH_LONG).show();

        } else {
            if (result.getStatus().equals(TransactionResult.STATUS_INVALID)) {
                Toast.makeText(this, "Transaction Invalid" + result.getResponse().getTransactionId(), Toast.LENGTH_LONG).show();
                Log.d("Pembayaran", "onTransactionFinished: " + result.getResponse().getTransactionId());
            } else {
                Toast.makeText(this, "Something Wrong", Toast.LENGTH_LONG).show();
            }
        }



    }


    public static String formatFirestoreTimestamp(Timestamp firestoreTimestamp) {
        // Convert Firestore timestamp to Java Date object
        Date dateObject = firestoreTimestamp.toDate();

        // Format the date to "dd-MMMM-yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        return dateFormat.format(dateObject);
    }






    private void deletketikagagal(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String bookingUid = getIntent().getStringExtra("DocumentID");

// Create a reference to the "Booking" document using the UID
        DocumentReference bookingRef = db.collection("Booking").document(bookingUid);

// Delete the document
        bookingRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Document successfully deleted
                        Toast.makeText(rincian_booking.this, "GAGAL TRANSAKSI", Toast.LENGTH_SHORT).show();
                        finish(); // Add any additional actions after successful deletion
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred during the deletion
                        Log.e("TAG", "Error deleting document", e);
                        Toast.makeText(rincian_booking.this, "Error deleting booking", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public TransactionRequest transactionRequest(String id, double price, int qty, String name) {

        double getTextfromtexttl = Double.parseDouble(total.getText().toString());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        TransactionRequest request = new TransactionRequest(Long.toString(System.currentTimeMillis()), getTextfromtexttl);
        ItemDetails details = new ItemDetails(id, (double) price, qty, name);
        ArrayList<ItemDetails> itemDetails = new ArrayList<>();
        itemDetails.add(details);
        request.setItemDetails(itemDetails);
        request.setCustomerDetails(new CustomerDetails("Customer", "MSJ Trans", auth.getCurrentUser().getEmail(), ""));
        CreditCard creditCard = new CreditCard();
        creditCard.setSaveCard(false);
        creditCard.setAuthentication(CreditCard.MIGS);
        request.setCreditCard(creditCard);
        return request;
    }


    private void clickPay() {
        try {
            double getTextfromtexttl = Double.parseDouble(total.getText().toString());

            MidtransSDK.getInstance().setTransactionRequest(transactionRequest(getIntent().getStringExtra("DocumentID"),getTextfromtexttl, 1,"Tiket Bus Cug"));
            MidtransSDK.getInstance().startPaymentUiFlow(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
