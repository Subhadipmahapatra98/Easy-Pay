package com.example.mobilevarificationotp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class signup extends AppCompatActivity {
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root,root1;
    String format,user,user1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        EditText mobile=findViewById(R.id.reg_mobile);
        EditText fname=findViewById(R.id.firstName);
        EditText lname=findViewById(R.id.lastName);
        EditText email=findViewById(R.id.email);
        Button btn=findViewById(R.id.reg_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile.getText().toString().trim().isEmpty()) {

                    mobile.setError("Please fill out this field");
                } else {
                    //Here you can write the codes for checking username
                }
                if (fname.getText().toString().trim().isEmpty()) {

                    fname.setError("Please fill out this field");
                } else {
                    //Here you can write the codes for checking firstname

                }
                if (lname.getText().toString().trim().isEmpty()) {

                    lname.setError("Please fill out this field");
                } else {
                    //Here you can write the codes for checking lastname
                }
                if (email.getText().toString().trim().isEmpty()) {

                    email.setError("Please fill out this field");
                } else {
                    //Here you can write the codes for checking email
                }
                SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
                format = s.format(new Date());
                user = mobile.getText().toString()+"-"+format;
                root=db.getReference().child("New User").child(user);
                user1 = mobile.getText().toString();
                root1=db.getReference().child("Balance").child(user1);
                HashMap<String,String> register=new HashMap<>();
                register.put("Mobile",mobile.getText().toString());
                String name = fname.getText().toString() + " " + lname.getText().toString();
                register.put("Name",name);
                register.put("Email",email.getText().toString());
                root.setValue(register);
                HashMap<String,String> register1=new HashMap<>();
                String wallet = "0";
                register1.put("Wallet",wallet);
                register1.put("Mobile",mobile.getText().toString());
                register1.put("Name",name);
                root1.setValue(register1);
                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(signup.this, "Login Here Now", Toast.LENGTH_SHORT).show();
            }
        });
    }
}