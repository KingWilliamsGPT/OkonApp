package com.example.touchbro;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailActivity extends AppCompatActivity {

    TextView tvName, tvMatric, tvAge, tvDept;
    StudentDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        tvName = findViewById(R.id.tvDetailName);
        tvMatric = findViewById(R.id.tvDetailMatric);
        tvAge = findViewById(R.id.tvDetailAge);
        tvDept = findViewById(R.id.tvDetailDept);

        dbHelper = new StudentDBHelper(this);

        int studentId = getIntent().getIntExtra("studentId", -1);
        if(studentId != -1){
            Cursor cursor = dbHelper.getStudentById(studentId);
            if(cursor != null && cursor.moveToFirst()){
                tvName.setText("Name: " + cursor.getString(cursor.getColumnIndexOrThrow("name")));
                tvMatric.setText("Matric No: " + cursor.getString(cursor.getColumnIndexOrThrow("matricNo")));
                tvAge.setText("Age: " + cursor.getInt(cursor.getColumnIndexOrThrow("age")));
                tvDept.setText("Department: " + cursor.getString(cursor.getColumnIndexOrThrow("department")));
                cursor.close();
            }
        }
    }
}
