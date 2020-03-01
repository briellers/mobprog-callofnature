package com.example.callofnature;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText username, pass, cpass;
    Button register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);
        register = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = username.getText().toString();
                String s2 = pass.getText().toString();
                String s3 = cpass.getText().toString();

                if(s1.equals("") || s2.equals("") || s3.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(s2.equals(s3)) {
                        Boolean checkUser = db.checkUser(s1);

                        if(checkUser == true) {
                            Boolean insert = db.insert(s1, s2);

                            if(insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }

                        else {
                            Toast.makeText(getApplicationContext(),"Email already exists.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

}
