package com.example.travelbuss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.travelbuss.adapter.AdapterMobil;
import com.example.travelbuss.models.MobilModels;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.protobuf.Option;
import com.example.travelbuss.models.MobilModels;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BerandaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BerandaFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btnboking;
    private TextView nama;
    private RecyclerView recyclerView;
    FirebaseAuth auth;


    private SharedPreferences sharedPreferences;

    public BerandaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BerandaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BerandaFragment newInstance(String param1, String param2) {
        BerandaFragment fragment = new BerandaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_beranda, container, false);
        btnboking=view.findViewById(R.id.button4);
        nama=view.findViewById(R.id.NamaUser);
 recyclerView = view.findViewById(R.id.viewberanda);
        auth = FirebaseAuth.getInstance();


        Query query = FirebaseFirestore.getInstance().collection("Data_Mobil");

        FirestoreRecyclerOptions<MobilModels> option = new FirestoreRecyclerOptions.Builder<MobilModels>()
                .setQuery(query, MobilModels.class)
                .build();

        AdapterMobil adapterMobil = new AdapterMobil(option);
        recyclerView.setAdapter(adapterMobil);
        recyclerView.setLayoutManager(new LinearLayoutManager(container != null ? container.getContext() : null, LinearLayoutManager.VERTICAL, false));;
        adapterMobil.startListening();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference dbReff = db.collection("Akunn").document(auth.getCurrentUser().getUid());
        dbReff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
             nama.setText("Selamat datang "+documentSnapshot.getString("Nama"));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        btnboking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getContext(), BookingActivity.class);
                startActivity(Intent);
            }
        });


        return view;

    }
}