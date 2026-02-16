package com.example.touchbro;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Page2Activity extends AppCompatActivity {

    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2); // make sure this matches your XML filename

        btnHome = findViewById(R.id.btnHome);

        btnHome.setOnClickListener(v -> {
            // Go back to MainActivity
            Intent intent = new Intent(Page2Activity.this, MainActivity.class);
            startActivity(intent);
            finish(); // optional: close this activity so back button doesn't come back here
        });
    }
}
