package com.example.touchbro;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {

    TextView tvName, tvMatric, tvAge, tvDept;
    Button btnEdit;
    int studentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        tvName = findViewById(R.id.tvDetailName);
        tvMatric = findViewById(R.id.tvDetailMatric);
        tvAge = findViewById(R.id.tvDetailAge);
        tvDept = findViewById(R.id.tvDetailDept);
        btnEdit = findViewById(R.id.btnEditStudent);

        studentId = getIntent().getIntExtra("studentId", -1);
        loadStudent();

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(StudentDetailActivity.this, AddStudentActivity.class);
            intent.putExtra("studentId", studentId);
            startActivity(intent);
        });
    }

    private void loadStudent() {
        if (studentId != -1) {
            ContentResolver resolver = getContentResolver();
            Uri studentUri = ContentUris.withAppendedId(StudentProvider.CONTENT_URI, studentId);
            Cursor cursor = resolver.query(studentUri, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                tvName.setText("Name: " + cursor.getString(cursor.getColumnIndexOrThrow("name")));
                tvMatric.setText("Matric No: " + cursor.getString(cursor.getColumnIndexOrThrow("matricNo")));
                tvAge.setText("Age: " + cursor.getInt(cursor.getColumnIndexOrThrow("age")));
                tvDept.setText("Department: " + cursor.getString(cursor.getColumnIndexOrThrow("department")));
                cursor.close();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudent();
    }
}
