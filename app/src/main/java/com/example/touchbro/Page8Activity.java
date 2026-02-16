package com.example.touchbro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Page8Activity extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4, cb5;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page8);

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        btnShow = findViewById(R.id.btnShow);

        btnShow.setOnClickListener(v -> {
            String selected = "Selected: ";
            if(cb1.isChecked()) selected += "Reading, ";
            if(cb2.isChecked()) selected += "Traveling, ";
            if(cb3.isChecked()) selected += "Gaming, ";
            if(cb4.isChecked()) selected += "Cooking, ";
            if(cb5.isChecked()) selected += "Sports, ";

            // Remove trailing comma
            if(selected.endsWith(", ")) selected = selected.substring(0, selected.length() - 2);

            Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
        });
    }
}
