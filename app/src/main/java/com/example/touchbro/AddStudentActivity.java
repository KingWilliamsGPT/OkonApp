package com.example.touchbro;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    EditText etMatric, etName, etAge, etDept;
    Button btnSubmit;
    int editStudentId = -1; // -1 means adding, otherwise editing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etMatric = findViewById(R.id.etMatric);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etDept = findViewById(R.id.etDept);
        btnSubmit = findViewById(R.id.btnSubmitStudent);

        // Check if we are editing an existing student
        editStudentId = getIntent().getIntExtra("studentId", -1);
        if (editStudentId != -1) {
            setTitle("Edit Student");
            btnSubmit.setText("Update Student");
            loadStudentData(editStudentId);
        } else {
            setTitle("Add Student");
        }

        btnSubmit.setOnClickListener(v -> {
            String matric = etMatric.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            String dept = etDept.getText().toString().trim();

            if (matric.isEmpty() || name.isEmpty() || ageStr.isEmpty() || dept.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            ContentResolver resolver = getContentResolver();
            ContentValues cv = new ContentValues();
            cv.put("matricNo", matric);
            cv.put("name", name);
            cv.put("age", age);
            cv.put("department", dept);

            if (editStudentId != -1) {
                // Update existing student via ContentProvider
                Uri studentUri = ContentUris.withAppendedId(StudentProvider.CONTENT_URI, editStudentId);
                int rows = resolver.update(studentUri, cv, null, null);
                if (rows > 0) {
                    Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to update student", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Insert new student via ContentProvider
                Uri result = resolver.insert(StudentProvider.CONTENT_URI, cv);
                if (result != null) {
                    Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadStudentData(int studentId) {
        ContentResolver resolver = getContentResolver();
        Uri studentUri = ContentUris.withAppendedId(StudentProvider.CONTENT_URI, studentId);
        Cursor cursor = resolver.query(studentUri, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            etMatric.setText(cursor.getString(cursor.getColumnIndexOrThrow("matricNo")));
            etName.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
            etAge.setText(String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("age"))));
            etDept.setText(cursor.getString(cursor.getColumnIndexOrThrow("department")));
            cursor.close();
        }
    }
}
