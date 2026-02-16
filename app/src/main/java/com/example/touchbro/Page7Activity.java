package com.example.touchbro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Page7Activity extends AppCompatActivity {

    EditText etStudentID, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page7);

        etStudentID = findViewById(R.id.etStudentID);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String id = etStudentID.getText().toString();
            String password = etPassword.getText().toString();

            if(id.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter Student ID and Password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
