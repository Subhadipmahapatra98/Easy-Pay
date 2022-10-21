package com.example.mobilevarificationotp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Home extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = view.findViewById(R.id.pay);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), paymoney.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Pay Money", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView bankpay = view.findViewById(R.id.bankpay);
        bankpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), bank_transfer.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Account Transfer Here", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView passbook = view.findViewById(R.id.passbook);
        passbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Your PassBook", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView cashback = view.findViewById(R.id.cashback);
        cashback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Your CashBacks", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView transactions = view.findViewById(R.id.transactions);
        transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(requireActivity(),userlist.class);
                startActivity(i);
                Toast.makeText(getActivity().getApplicationContext(), "Transaction History", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView store = view.findViewById(R.id.store);
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Buy New Stuff Here", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView recharge = view.findViewById(R.id.pre_mob);
        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), recharge.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Recharge Here", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView postrecharge = view.findViewById(R.id.post_mob);
        postrecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), recharge.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Recharge Here", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView elec = view.findViewById(R.id.electricity);
        elec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView gpay123 = view.findViewById(R.id.googlepay);
        gpay123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView water123 = view.findViewById(R.id.water);
        water123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView utilities123 = view.findViewById(R.id.utilities);
        utilities123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView train123 = view.findViewById(R.id.train);
        train123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView bill123= view.findViewById(R.id.bills);
        bill123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView dth123 = view.findViewById(R.id.dth);
        dth123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView toys123 = view.findViewById(R.id.toys);
        toys123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView food123 = view.findViewById(R.id.food);
        food123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView more123 = view.findViewById(R.id.more);
        more123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), Under_Progress.class);
                startActivity(intent);
                Toast.makeText(getActivity().getApplicationContext(), "Under Progress", Toast.LENGTH_SHORT).show();
            }
        });
    }
}