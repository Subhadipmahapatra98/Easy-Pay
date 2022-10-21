package com.example.mobilevarificationotp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class wallet_addmoney extends AppCompatActivity {
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private String option_for_addmoney = "";
    String user,format,am,bal1,mobile,name,value;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_addmoney);
        Button addmoney = findViewById(R.id.wallet2);
        EditText money = findViewById(R.id.input_amount);
        RadioGroup radio = findViewById(R.id.radioGroup);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        user = sharedPref.getString("user", "Not Available");
        Toast.makeText(this, "Number : " + user, Toast.LENGTH_SHORT).show();
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        format = s.format(new Date());
        am = user + "-" + format;
        DatabaseReference root = db.getReference().child("Add Money").child(am);
        DatabaseReference root1 = db.getReference().child("Transactions").child(am);

        addmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton choose_option = (RadioButton) findViewById(radio.getCheckedRadioButtonId());
                option_for_addmoney = choose_option.getText().toString();
                radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                        switch (i) {
                            case R.id.gpay:
                                option_for_addmoney = "GPay";
                                break;
                            case R.id.phonepe:
                                option_for_addmoney = "PhonePe";
                                break;
                            case R.id.paytm:
                                option_for_addmoney = "Paytm";
                                break;
                            case R.id.credit_c:
                                option_for_addmoney = "Credit Card";
                                break;
                            case R.id.debit:
                                option_for_addmoney = "Debit Card";
                                break;
                        }
                    }
                });

                reference = FirebaseDatabase.getInstance().getReference("Balance");
                reference.child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                Toast.makeText(wallet_addmoney.this, "Fetched successfully", Toast.LENGTH_SHORT).show();
                                DataSnapshot dataSnapshot = task.getResult();
                                bal1 = String.valueOf(dataSnapshot.child("Wallet").getValue());
                                mobile = String.valueOf(dataSnapshot.child("Mobile").getValue());
                                name = String.valueOf(dataSnapshot.child("Name").getValue());
                                int i=Integer.parseInt(bal1);
                                int i1=Integer.parseInt(money.getText().toString());
                                int i2=i+i1;
                                value = Integer.toString(i2);
                                reference.child(user).child("Wallet").setValue(value);
                            } else {
                                Toast.makeText(wallet_addmoney.this, "Data doesn't exist", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(wallet_addmoney.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                String amount = money.getText().toString();
                HashMap<String, String> addmoney = new HashMap<>();
                addmoney.put("Name",user);
                addmoney.put("Amount", amount);
                addmoney.put("Option", option_for_addmoney);
                addmoney.put("TT","Credit in Wallet");
                root.setValue(addmoney);
                root1.setValue(addmoney);
                Intent intent = new Intent(wallet_addmoney.this, Transaction_Successful.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Transaction Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}