package com.example.mobilevarificationotp;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class recharge extends AppCompatActivity {
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root;
    private DatabaseReference root1;
    DatabaseReference reference;
    String bal1,value;
    String user,format,pr;
    private String plan_amount = "";
    private String plan_operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        Button recharge = findViewById(R.id.recharge);
        EditText number=findViewById(R.id.Mobnum);
        RadioGroup frist=findViewById(R.id.option1);
        RadioGroup second=findViewById(R.id.option);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        user = sharedPref.getString("user", "Not Available");
        Toast.makeText(this, "Number : "+user, Toast.LENGTH_SHORT).show();
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        format = s.format(new Date());
        pr = user+"-"+format;
        root=db.getReference().child("Recharge").child(pr);
        root1=db.getReference().child("Transactions").child(pr);

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rechargeamount=(RadioButton) findViewById(frist.getCheckedRadioButtonId());
                plan_amount = rechargeamount.getText().toString();
                frist.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                        switch (i){
                            case R.id.rec199:
                                plan_amount = "199";
                                break;
                            case R.id.rec599:
                                plan_amount = "599";
                                break;
                            case R.id.rec899:
                                plan_amount = "899";
                                break;
                        }
                    }
                });

                RadioButton rechargeoperator=(RadioButton) findViewById(second.getCheckedRadioButtonId());
                plan_operator = rechargeoperator.getText().toString();
                second.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                        switch (i){
                            case R.id.gpay:
                                plan_operator = "Jio";
                                break;
                            case R.id.phonepe:
                                plan_operator = "Airtel";
                                break;
                            case R.id.paytm:
                                plan_operator = "Vodaphone";
                                break;
                            case R.id.credit_c:
                                plan_operator = "BSNL";
                                break;
                        }
                    }
                });
                String mnumber=number.getText().toString();
                HashMap<String,String> recharge=new HashMap<>();
                recharge.put("Phone Number",mnumber);
                recharge.put("Name",mnumber);
                recharge.put("Amount",plan_amount);
                recharge.put("Recharge Operator",plan_operator);
                recharge.put("TT","Recharge");
                root.setValue(recharge);
                root1.setValue(recharge);

                reference = FirebaseDatabase.getInstance().getReference("Balance");
                reference.child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                Toast.makeText(recharge.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                                DataSnapshot dataSnapshot = task.getResult();
                                bal1 = String.valueOf(dataSnapshot.child("Wallet").getValue());
                                int i=Integer.parseInt(bal1);
                                int i1=Integer.parseInt(rechargeamount.getText().toString());
                                if(i<i1)
                                {
                                    Toast.makeText(recharge.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    int i2 = i - i1;
                                    value = Integer.toString(i2);
                                    reference.child(user).child("Wallet").setValue(value);

                                }
                            } else {
                                Toast.makeText(recharge.this, "Data doesn't exist", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(recharge.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Intent intent = new Intent(com.example.mobilevarificationotp.recharge.this, Transaction_Successful.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Transaction Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}