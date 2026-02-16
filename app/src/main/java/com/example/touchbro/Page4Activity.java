package com.example.touchbro;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

public class Page4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);

        // TextView to show Bluetooth status
        TextView status = new TextView(this);
        status.setTextSize(18);
        status.setText("Bluetooth is OFF");

        // Toggle Button
        ToggleButton toggle = new ToggleButton(this);
        toggle.setTextOn("Bluetooth ON");
        toggle.setTextOff("Bluetooth OFF");
        toggle.setChecked(false);

        toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                status.setText("Bluetooth is ON");
            } else {
                status.setText("Bluetooth is OFF");
            }
        });

        layout.addView(toggle);
        layout.addView(status);

        setContentView(layout);
    }
}
