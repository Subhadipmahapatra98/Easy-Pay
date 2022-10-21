package com.example.mobilevarificationotp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class bank_transfer extends AppCompatActivity {
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root;
    private DatabaseReference root1;
    DatabaseReference reference;
    String bal1,value;
    String user,format,bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_transfer);
        EditText name=findViewById(R.id.bank_name);
        EditText bankacno=findViewById(R.id.bank_acno);
        EditText bankcnf=findViewById(R.id.bank_cnfacno);
        EditText ifsc=findViewById(R.id.bank_ifsc);
        EditText amount=findViewById(R.id.bank_amount);
        Button send = findViewById(R.id.send);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        user = sharedPref.getString("user", "Not Available");
        Toast.makeText(this, "Number : "+user, Toast.LENGTH_SHORT).show();
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        format = s.format(new Date());
        bt = user+"-"+format;
        root=db.getReference().child("Bank Transfer").child(bt);
        root1=db.getReference().child("Transactions").child(bt);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mname=name.getText().toString();
                String account=bankacno.getText().toString();
                String cnfaccount=bankcnf.getText().toString();
                String bifsc=ifsc.getText().toString();
                String bamount=amount.getText().toString();
                HashMap<String,String> bankdetails=new HashMap<>();
                bankdetails.put("Name",mname);
                bankdetails.put("Account",account);
                bankdetails.put("Confirm Account",cnfaccount);
                bankdetails.put("IFSC",bifsc);
                bankdetails.put("Amount",bamount);
                bankdetails.put("TT","Debit Account");
                root.setValue(bankdetails);
                root1.setValue(bankdetails);

                reference = FirebaseDatabase.getInstance().getReference("Balance");
                reference.child(user).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                Toast.makeText(bank_transfer.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                                DataSnapshot dataSnapshot = task.getResult();
                                bal1 = String.valueOf(dataSnapshot.child("Wallet").getValue());
                                int i=Integer.parseInt(bal1);
                                int i1=Integer.parseInt(amount.getText().toString());
                                if(i<i1)
                                {
                                    Toast.makeText(bank_transfer.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    int i2 = i - i1;
                                    value = Integer.toString(i2);
                                    reference.child(user).child("Wallet").setValue(value);

                                }
                            } else {
                                Toast.makeText(bank_transfer.this, "Data doesn't exist", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(bank_transfer.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Intent intent = new Intent(bank_transfer.this, Transaction_Successful.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Transaction Successful", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

