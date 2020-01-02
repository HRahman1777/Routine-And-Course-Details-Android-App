package com.example.diupc_b49th_batchcse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Notice extends AppCompatActivity {

    Button buttonNoticerefresh;
    TextView textViewNotice;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        buttonNoticerefresh = findViewById(R.id.noticeRefreshBtnId);
        textViewNotice = findViewById(R.id.noticeTextViewId);

        display();
        display2();
        buttonNoticerefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("notice");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String noticeS = dataSnapshot.getValue().toString();

                        SharedPreferences sharedPreferences = getSharedPreferences("noticeK", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("on2",noticeS);
                        editor.commit();
                        Toast.makeText(Notice.this, "Notice Already Updated!!\n\t\t\t\t\t\t\t(^_^)",Toast.LENGTH_SHORT).show();
                        textViewNotice.setText(noticeS);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        display2();

    }

    private void display2() {
        SharedPreferences sharedPreferences2 = getSharedPreferences("noticeK", Context.MODE_PRIVATE);
        String one2 = sharedPreferences2.getString("on2","Need Internet For First Time And Press Refresh For Updates");
        textViewNotice.setText(one2);
    }

    private void display() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("notice");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String noticeS = dataSnapshot.getValue().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("noticeK", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("on2",noticeS);
                editor.commit();
                textViewNotice.setText(noticeS);
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
