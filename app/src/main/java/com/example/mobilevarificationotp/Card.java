package com.example.mobilevarificationotp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Intent intent = new Intent(Card.this, Transaction_Successful.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Transaction Successful", Toast.LENGTH_SHORT).show();
    }
}