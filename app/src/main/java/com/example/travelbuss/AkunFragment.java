package com.example.travelbuss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AkunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AkunFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView satu, dua, tiga, empat, lima, enam, tujuh, delap;
    private TextView siji, loro, telu, papat;
    private Button logout;
    private FirebaseAuth auth;

    public AkunFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AkunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AkunFragment newInstance(String param1, String param2) {
        AkunFragment fragment = new AkunFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_akun, container, false);
        // Inflate the layout for this fragment

        satu = view.findViewById(R.id.btndataakun);
        dua = view.findViewById(R.id.btntentangmitra);
        tiga = view.findViewById(R.id.btnpusatbantuan);
        empat = view.findViewById(R.id.btnsyaratdnaketentuan);
        lima = view.findViewById(R.id.syarat);
        papat = view.findViewById(R.id.ketentuan);
        enam = view.findViewById(R.id.pusat);
        tujuh = view.findViewById(R.id.mitra);
        delap = view.findViewById(R.id.akun);
        siji = view.findViewById(R.id.ubah);
        loro = view.findViewById(R.id.about);
        telu = view.findViewById(R.id.bantuan);
        logout = view.findViewById(R.id.logout);


        satu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getContext(), dataprofileActivity.class);
                startActivity(Intent);

            }
        });

        dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getContext(), ProfileMitraActivity.class);
                startActivity(Intent);
            }
        });

        tiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getContext(), pusatbantuanActivity.class);
                startActivity(Intent);

            }
        });

        empat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent Intent = new Intent(getContext(), syaratketentuanActivity.class);
//                startActivity(Intent);

            }
        });
        lima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent Intent = new Intent(getContext(), syaratketentuanActivity.class);
//                startActivity(Intent);
            }
        });
         enam.setOnClickListener(new View.OnClickListener() {
             @Override
         public void onClick(View view) {
               Intent Intent = new Intent(getContext(), pusatbantuanActivity.class);
               startActivity(Intent);
             }
        });
         tujuh.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent Intent = new Intent(getContext(), ProfileMitraActivity.class);
                 startActivity(Intent);
             }
         });
         delap.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent Intent = new Intent(getContext(), dataprofileActivity.class);
                 startActivity(Intent);
             }
         });
         siji.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent Intent = new Intent(getContext(), dataprofileActivity.class);
                 startActivity(Intent);
             }
         });
         loro.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent Intent = new Intent(getContext(), ProfileMitraActivity.class);
                 startActivity(Intent);
             }
         });
         telu.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent Intent = new Intent(getContext(), pusatbantuanActivity.class);
                 startActivity(Intent);
             }
         });
         papat.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
//                     Intent Intent = new Intent(getContext(), syaratketentuanActivity.class);
//                     startActivity(Intent);
             }
         });
         logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 auth.signOut();

                 // Navigate to login screen
                 Intent intent = new Intent(view.getContext(), LoginActivity.class);

                 startActivity(intent);

                 // Close current activity
                 getActivity().finish();

             }

         });
        return view;


    }
}