package com.example.mobilevarificationotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dashbord extends AppCompatActivity {

    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
//    TextView mob,name;

//    private FirebaseDatabase db=FirebaseDatabase.getInstance();
//    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Easy Pay");
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        mob=findViewById(R.id.mobnum);
//        name=findViewById(R.id.uname);


        Intent intent = getIntent();
        String mobile = intent.getStringExtra("mobile");
//        String mobile = "9972791039";
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user",mobile);
        editor.commit();

//
//        reference = FirebaseDatabase.getInstance().getReference("Balance");
//        reference.child(mobile).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    if (task.getResult().exists()) {
//                        Toast.makeText(dashbord.this, "Fetched successfully", Toast.LENGTH_SHORT).show();
//                        DataSnapshot dataSnapshot = task.getResult();
//                        String mobile1 = String.valueOf(dataSnapshot.child("Mobile").getValue());
//                        String name1 = String.valueOf(dataSnapshot.child("Name").getValue());
//                        mob.setText(mobile1);
//                        name.setText(name1);
//                        reference.child(mobile).child("Mobile").setValue(mob);
//                        reference.child(mobile).child("Name").setValue(name);
//                    } else {
//                        Toast.makeText(dashbord.this, "Data doesn't exist", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(dashbord.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private NavigationBarView.OnItemSelectedListener navListener = new BottomNavigationView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new Home();
                    break;
                case R.id.nav_mall:
                    selectedFragment = new Mall();
                    break;
                case R.id.nav_wallet:
                    selectedFragment = new wallet();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}