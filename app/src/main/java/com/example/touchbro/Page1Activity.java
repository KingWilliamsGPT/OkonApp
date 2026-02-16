package com.example.touchbro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Page1Activity extends AppCompatActivity {

    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(v ->
                startActivity(new Intent(Page1Activity.this, MainActivity.class)));
    }
}
