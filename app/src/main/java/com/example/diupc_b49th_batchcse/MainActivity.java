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

public class MainActivity extends AppCompatActivity {

    Button buttonRoutine, buttonCourseInfo, buttonNotice;
    TextView textViewHeading;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRoutine = findViewById(R.id.routineBtn);
        buttonCourseInfo = findViewById(R.id.courseInfoBtn);
        buttonNotice = findViewById(R.id.noticeBtn);
        textViewHeading = findViewById(R.id.courseTextView);

        ///Heading
        heading();
        heading2();
        //end Heading

        buttonRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent((MainActivity.this) , (Routine.class));
                startActivity(intent);
            }
        });

        buttonCourseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((MainActivity.this) , (CourseInformation.class));
                startActivity(intent);
            }
        });

        buttonNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((MainActivity.this) , (Notice.class));
                startActivity(intent);
            }
        });
    }
    private void heading2() {
        SharedPreferences sharedPreferences2 = getSharedPreferences("hd", Context.MODE_PRIVATE);
        String one = sharedPreferences2.getString("on","Welcome");
        textViewHeading.setText(one);
    }

    private void heading() {

        databaseReference = FirebaseDatabase.getInstance().getReference().child("heading");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String h1 = dataSnapshot.getValue().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("hd", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("on",h1);
                editor.commit();
                textViewHeading.setText(h1);
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
