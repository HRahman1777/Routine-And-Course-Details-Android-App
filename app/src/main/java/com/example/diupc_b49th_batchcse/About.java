package com.example.diupc_b49th_batchcse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends AppCompatActivity {

    Button buttonBackAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        buttonBackAbout = findViewById(R.id.aboutBackBtnId);

        buttonBackAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((About.this) , (MainActivity.class));
                startActivity(intent);
                finish();
            }
        });

    }
}
