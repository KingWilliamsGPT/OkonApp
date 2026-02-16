package com.example.touchbro;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class Page9Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter adapter;
    ArrayList<Student> studentList;
    EditText searchBar;
    FloatingActionButton fab;
    TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page9);

        recyclerView = findViewById(R.id.studentRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchBar = findViewById(R.id.searchBar);
        fab = findViewById(R.id.fabAddStudent);
        tvEmpty = findViewById(R.id.tvEmpty);

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
        ContentResolver resolver = getContentResolver();
        Uri uri = StudentProvider.CONTENT_URI;
        String selection = null;
        String[] selectionArgs = null;

        if(!query.isEmpty()){
            selection = "name LIKE ? OR matricNo LIKE ?";
            selectionArgs = new String[]{"%" + query + "%", "%" + query + "%"};
        }

        Cursor cursor = resolver.query(uri, null, selection, selectionArgs, null);

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

        // Show or hide "No students" message
        if(studentList.isEmpty()){
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        loadStudents(searchBar.getText().toString());
        adapter.notifyDataSetChanged();
    }
}
