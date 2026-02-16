package com.example.touchbro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnPage1, btnPage2, btnPage3, btnPage4, btnPage5, btnPage6, btnPage7, btnPage8, btnPage9, btnPage10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPage1 = findViewById(R.id.btnPage1);
        btnPage2 = findViewById(R.id.btnPage2);
        btnPage3 = findViewById(R.id.btnPage3);
        btnPage4 = findViewById(R.id.btnPage4);
        btnPage5 = findViewById(R.id.btnPage5);
        btnPage6 = findViewById(R.id.btnPage6);
        btnPage7 = findViewById(R.id.btnPage7);
        btnPage8 = findViewById(R.id.btnPage8);
        btnPage9 = findViewById(R.id.btnPage9);
        btnPage10 = findViewById(R.id.btnPage10);

        btnPage1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page1Activity.class)));
        btnPage2.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page2Activity.class)));
        btnPage3.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page3Activity.class)));
        btnPage4.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page4Activity.class)));
        btnPage5.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page5Activity.class)));
        btnPage6.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page6Activity.class)));
        btnPage7.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page7Activity.class)));
        btnPage8.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page8Activity.class)));
        btnPage9.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page9Activity.class)));
        btnPage10.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Page10Activity.class)));
    }
}
