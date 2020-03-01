package com.example.callofnature;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button yes, no;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "You clicked yes", Toast.LENGTH_SHORT).show();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DashboardActivity.this, "You clicked no", Toast.LENGTH_SHORT).show();
            }
        });
    }

}