package com.example.mobilevarificationotp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link wallet#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class wallet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseReference reference;
    TextView bal;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment wallet.
     */
    // TODO: Rename and change types and number of parameters
    public static wallet newInstance(String param1, String param2) {
        wallet fragment = new wallet();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public wallet() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        String user = sharedPref.getString("user", "Not Available");
        readData(user);

    }

    public void readData(String user) {
        reference = FirebaseDatabase.getInstance().getReference("Balance");
        reference.child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(getActivity().getApplicationContext(),"Fetched successfully",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String bal1 = String.valueOf(dataSnapshot.child("Wallet").getValue());
                        bal = getActivity().findViewById(R.id.balance);
                        bal.setText(bal1);
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(),"Data doesn't exist",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Failed to fetch data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton imagebtn = view.findViewById(R.id.wallet1);
        Button sendmoney = view.findViewById(R.id.button11);
        sendmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), paymoney.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Send Money Here", Toast.LENGTH_SHORT).show();
            }
        });
        

        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(requireActivity(), wallet_addmoney.class);
                    startActivity(intent);
                    Toast.makeText(getActivity().getApplicationContext(), "Add Money Here", Toast.LENGTH_SHORT).show();
                }
        });
    }
}