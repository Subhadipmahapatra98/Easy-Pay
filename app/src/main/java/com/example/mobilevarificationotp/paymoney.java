package com.example.mobilevarificationotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class paymoney extends AppCompatActivity {
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root;
    private DatabaseReference root1;
    DatabaseReference reference;
    String bal1,value,bal2,value1;
    String user,format,pm,mob,mob1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymoney);
        Button send_money = findViewById(R.id.sendmoney);
        EditText name=findViewById(R.id.pay_name);
        EditText mobnum=findViewById(R.id.pay_mobile);
        EditText amount=findViewById(R.id.pay_amount);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        user = sharedPref.getString("user", "Not Available");
        Toast.makeText(this, "Number : "+user, Toast.LENGTH_SHORT).show();
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        format = s.format(new Date());
        pm = user+"-"+format;
        root=db.getReference().child("Pay Money").child(pm);
        root1=db.getReference().child("Transactions").child(pm);

        send_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mname=name.getText().toString();
                String number=mobnum.getText().toString();
                String pamount=amount.getText().toString();
                HashMap<String,String> paymoney=new HashMap<>();
                paymoney.put("Name",mname);
                paymoney.put("Account",number);
                paymoney.put("Amount",pamount);
                paymoney.put("TT","Debit Wallet");
                root.setValue(paymoney);
                root1.setValue(paymoney);

                reference = FirebaseDatabase.getInstance().getReference("Balance");
                reference.child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                Toast.makeText(paymoney.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                                DataSnapshot dataSnapshot = task.getResult();
                                bal1 = String.valueOf(dataSnapshot.child("Wallet").getValue());
                                int i=Integer.parseInt(bal1);
                                int i1=Integer.parseInt(amount.getText().toString());
                                if(i<i1)
                                {
                                    Toast.makeText(paymoney.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    int i2 = i - i1;
                                    value = Integer.toString(i2);
                                    reference.child(user).child("Wallet").setValue(value);
                                }
                            } else {
                                Toast.makeText(paymoney.this, "Data doesn't exist", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(paymoney.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                reference = FirebaseDatabase.getInstance().getReference("Balance");
                reference.child(mobnum.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
//                                Toast.makeText(paymoney.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                                DataSnapshot dataSnapshot = task.getResult();
                                bal2 = String.valueOf(dataSnapshot.child("Wallet").getValue());
                                int i=Integer.parseInt(bal2);
                                int i1=Integer.parseInt(amount.getText().toString());
                                int i2 = i + i1;
                                value1 = Integer.toString(i2);
                                reference.child(mobnum.getText().toString()).child("Wallet").setValue(value1);

                            } else {
                                Toast.makeText(paymoney.this, "Data doesn't exist", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(paymoney.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Intent intent = new Intent(paymoney.this, Transaction_Successful.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Transaction Successful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}