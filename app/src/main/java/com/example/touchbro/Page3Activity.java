package com.example.touchbro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Page3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        Button myButton = findViewById(R.id.myButton);

        // You can handle click here
        myButton.setOnClickListener(v ->
                Toast.makeText(Page3Activity.this, "Button clicked!", Toast.LENGTH_SHORT).show()
        );
    }
}
