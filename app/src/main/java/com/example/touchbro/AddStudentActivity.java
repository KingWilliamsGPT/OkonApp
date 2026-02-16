package com.example.touchbro;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    EditText etMatric, etName, etAge, etDept;
    Button btnSubmit;
    StudentDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etMatric = findViewById(R.id.etMatric);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etDept = findViewById(R.id.etDept);
        btnSubmit = findViewById(R.id.btnSubmitStudent);

        dbHelper = new StudentDBHelper(this);

        btnSubmit.setOnClickListener(v -> {
            String matric = etMatric.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            String dept = etDept.getText().toString().trim();

            if(matric.isEmpty() || name.isEmpty() || ageStr.isEmpty() || dept.isEmpty()){
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            long id = dbHelper.insertStudent(matric, name, age, dept, null);
            if(id != -1){
                Toast.makeText(this, "Student added", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
