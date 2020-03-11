package com.example.callofnature;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button yes, no;
    Button profile, help, settings;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        profile = findViewById(R.id.profile);
        help = findViewById(R.id.help);
        settings = findViewById(R.id.settings);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes(v);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no(v);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void yes(View v) {
        DBase db = new DBase(this);
        db.open();
        db.add("Yes");
        db.close();

        Dialog d = new Dialog(this);
        d.setTitle("Message");
        TextView tv = new TextView(this);
        tv.setText("Record successfully updated!");
        d.setContentView(tv);
        d.show();
    }

    public void no(View v) {
        DBase db = new DBase(this);
        db.open();
        db.add("No");
        db.close();

        Dialog d = new Dialog(this);
        d.setTitle("Message");
        TextView tv = new TextView(this);
        tv.setText("Record successfully updated!");
        d.setContentView(tv);
        d.show();
    }

}