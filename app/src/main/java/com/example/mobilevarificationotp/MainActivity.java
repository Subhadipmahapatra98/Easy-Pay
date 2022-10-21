package com.example.mobilevarificationotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MainActivity  extends AppCompatActivity {
    EditText enternumber, reg_mobile, reg_firstName, reg_lastName, reg_email;;
    Button getotpbutton,newuser,reg_register;
    TextInputLayout txtInLayoutRegPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enternumber = findViewById(R.id.input_amount);
        getotpbutton = findViewById(R.id.getotpbutton);
        newuser = findViewById(R.id.newuser);



        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "New User Register here", Toast.LENGTH_SHORT).show();

            }
        });
        ProgressBar progressbar = findViewById(R.id.progressbar_sending_otp);

        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enternumber.getText().toString().trim().isEmpty()) {
                    if ((enternumber.getText().toString().trim()).length() == 10) {
                        progressbar.setVisibility(View.VISIBLE);
                        getotpbutton.setVisibility(View.INVISIBLE);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + enternumber.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            MainActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressbar.setVisibility(View.GONE);
                                    getotpbutton.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressbar.setVisibility(View.GONE);
                                    getotpbutton.setVisibility(View.VISIBLE);
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    {
                                        progressbar.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), VarificationOtp2.class);
                                        intent.putExtra("mobile", enternumber.getText().toString());
                                        intent.putExtra("backendotp", backendotp);
                                        startActivity(intent);

                                    }
                                }
                            });
                    } else {
                        Toast.makeText(MainActivity.this, "Enter correct mobile number", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Enter a mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}