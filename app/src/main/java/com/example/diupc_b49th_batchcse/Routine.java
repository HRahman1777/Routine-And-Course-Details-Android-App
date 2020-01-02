package com.example.diupc_b49th_batchcse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Routine extends AppCompatActivity {

    Button buttonRefresh, buttonSaturday, buttonSunday, buttonMonday, buttonTuesday, buttonWednesday, buttonThursday;

    TextView textViewRoutine;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        advanced();

        this.setTitle("Routine");

        buttonRefresh = findViewById(R.id.refreshBtnId);
        buttonSaturday = findViewById(R.id.saturdayBtnId);
        buttonSunday = findViewById(R.id.sundayBtnId);
        buttonMonday = findViewById(R.id.mondayBtnId);
        buttonTuesday = findViewById(R.id.tuesdayBtnId);
        buttonWednesday = findViewById(R.id.wednesdayBtnId);
        buttonThursday = findViewById(R.id.thursdayBtnId);
        textViewRoutine = findViewById(R.id.routineTxtViewId);

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("routine");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String sat = dataSnapshot.child("saturday").getValue().toString();
                        String sun = dataSnapshot.child("sunday").getValue().toString();
                        String mon = dataSnapshot.child("monday").getValue().toString();
                        String tues = dataSnapshot.child("tuesday").getValue().toString();
                        String wed = dataSnapshot.child("wednesday").getValue().toString();
                        String thur = dataSnapshot.child("thursday").getValue().toString();

                        SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("sa",sat);
                        editor.putString("su", sun);
                        editor.putString("mo", mon);
                        editor.putString("tu", tues);
                        editor.putString("we", wed);
                        editor.putString("th", thur);
                        editor.commit();
                        Toast.makeText(Routine.this, "Routine Already Updated!!\n\t\t\t\t\t\t\t(^_^)",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        buttonSaturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                String one = sharedPreferences.getString("sa","Press Refresh");
                textViewRoutine.setText(one);
            }
        });

        buttonSunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                String one = sharedPreferences.getString("su","Press Refresh");
                textViewRoutine.setText(one);
            }
        });

        buttonMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                String one = sharedPreferences.getString("mo","Press Refresh");
                textViewRoutine.setText(one);
            }
        });

        buttonTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                String one = sharedPreferences.getString("tu","Press Refresh");
                textViewRoutine.setText(one);
            }
        });

        buttonWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                String one = sharedPreferences.getString("we","Press Refresh");
                textViewRoutine.setText(one);
            }
        });

        buttonThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                String one = sharedPreferences.getString("th","Press Refresh");
                textViewRoutine.setText(one);
            }
        });


    }

    private void advanced() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("routine");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sat = dataSnapshot.child("saturday").getValue().toString();
                String sun = dataSnapshot.child("sunday").getValue().toString();
                String mon = dataSnapshot.child("monday").getValue().toString();
                String tues = dataSnapshot.child("tuesday").getValue().toString();
                String wed = dataSnapshot.child("wednesday").getValue().toString();
                String thur = dataSnapshot.child("thursday").getValue().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("rou", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("sa",sat);
                editor.putString("su", sun);
                editor.putString("mo", mon);
                editor.putString("tu", tues);
                editor.putString("we", wed);
                editor.putString("th", thur);
                editor.commit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.adminID){
            Intent intent = new Intent((getApplication()) , (LogIn.class));
            startActivity(intent);
        }
        if(item.getItemId()==R.id.aboutID){
            Intent intent = new Intent((getApplication()) , (About.class));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
