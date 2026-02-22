package com.example.okonapp;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class ControlsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);

        // Bluetooth Toggle logic
        ToggleButton tbBluetooth = findViewById(R.id.tb_bluetooth);
        tbBluetooth.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "Bluetooth ON" : "Bluetooth OFF";
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
        });

        // Checkboxes logic
        CheckBox[] checkBoxes = {
                findViewById(R.id.cb1), findViewById(R.id.cb2),
                findViewById(R.id.cb3), findViewById(R.id.cb4),
                findViewById(R.id.cb5)
        };

        findViewById(R.id.btn_toast_checkboxes).setOnClickListener(v -> {
            List<String> selected = new ArrayList<>();
            for (CheckBox cb : checkBoxes) {
                if (cb.isChecked()) {
                    selected.add(cb.getText().toString());
                }
            }
            if (selected.isEmpty()) {
                Toast.makeText(this, "No interests selected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Selected: " + String.join(", ", selected), Toast.LENGTH_SHORT).show();
            }
        });

        // Standalone Radio Buttons logic
        RadioButton rb1 = findViewById(R.id.rb_standalone1);
        RadioButton rb2 = findViewById(R.id.rb_standalone2);

        rb1.setOnClickListener(v -> Toast.makeText(this, "Selected: " + rb1.getText(), Toast.LENGTH_SHORT).show());
        rb2.setOnClickListener(v -> Toast.makeText(this, "Selected: " + rb2.getText(), Toast.LENGTH_SHORT).show());

        // Radio Group logic
        RadioGroup rg = findViewById(R.id.rg_options);
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRb = findViewById(checkedId);
            if (selectedRb != null) {
                Toast.makeText(this, "Group Selection: " + selectedRb.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
