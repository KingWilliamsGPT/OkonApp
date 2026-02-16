package com.example.touchbro;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class Page9Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter adapter;
    ArrayList<Student> studentList;
    StudentDBHelper dbHelper;
    EditText searchBar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page9);

        recyclerView = findViewById(R.id.studentRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchBar = findViewById(R.id.searchBar);
        fab = findViewById(R.id.fabAddStudent);

        dbHelper = new StudentDBHelper(this);
        studentList = new ArrayList<>();
        loadStudents("");

        adapter = new StudentAdapter(this, studentList);
        recyclerView.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count){}
            @Override public void afterTextChanged(Editable s){
                loadStudents(s.toString());
                adapter.notifyDataSetChanged();
            }
        });

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(Page9Activity.this, AddStudentActivity.class);
            startActivity(intent);
        });
    }

    private void loadStudents(String query){
        studentList.clear();
        Cursor cursor;
        if(query.isEmpty()){
            cursor = dbHelper.getAllStudents();
        } else {
            cursor = dbHelper.searchStudents(query);
        }

        if(cursor != null){
            while(cursor.moveToNext()){
                studentList.add(new Student(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("matricNo")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("age")),
                        cursor.getString(cursor.getColumnIndexOrThrow("department")),
                        cursor.getBlob(cursor.getColumnIndexOrThrow("profilePic"))
                ));
            }
            cursor.close();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadStudents(searchBar.getText().toString());
        adapter.notifyDataSetChanged();
    }
}
