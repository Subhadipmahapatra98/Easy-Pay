package com.example.mobilevarificationotp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Transaction_Successful extends AppCompatActivity {
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_successful);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(this)
                .load("https://cdn.dribbble.com/users/39201/screenshots/3694057/media/2a1b062114a8244102f67deeb89395fa.gif")
                .into(imageView);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        user = sharedPref.getString("user", "Not Available");
        Button done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transaction_Successful.this, dashbord.class);
                intent.putExtra("mobile", user);
                startActivity(intent);
            }
        });
    }
}