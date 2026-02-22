package com.example.okonapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.card.MaterialCardView;

public class StudentInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        EditText etName = findViewById(R.id.et_student_name);
        EditText etRoll = findViewById(R.id.et_roll_no);
        EditText etDept = findViewById(R.id.et_dept);
        MaterialCardView cardView = findViewById(R.id.card_info_display);
        TextView tvName = findViewById(R.id.tv_display_name);
        TextView tvRoll = findViewById(R.id.tv_display_roll);
        TextView tvDept = findViewById(R.id.tv_display_dept);

        findViewById(R.id.btn_display_info).setOnClickListener(v -> {
            String name = etName.getText().toString();
            String roll = etRoll.getText().toString();
            String dept = etDept.getText().toString();

            if (!name.isEmpty()) {
                tvName.setText("Name: " + name);
                tvRoll.setText("Roll No: " + roll);
                tvDept.setText("Department: " + dept);
                cardView.setVisibility(View.VISIBLE);
            }
        });
    }
}
