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
    NoteAdapter adapter;
    ArrayList<Note> noteList;
    EditText searchBar;
    FloatingActionButton fab;
    TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page9);
        setTitle("Page 9 - Notes");

        recyclerView = findViewById(R.id.noteRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchBar = findViewById(R.id.searchBar);
        fab = findViewById(R.id.fabAddNote);
        tvEmpty = findViewById(R.id.tvEmpty);

        noteList = new ArrayList<>();
        loadNotes("");

        adapter = new NoteAdapter(this, noteList);
        recyclerView.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count){}
            @Override public void afterTextChanged(Editable s){
                loadNotes(s.toString());
                adapter.notifyDataSetChanged();
            }
        });

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(Page9Activity.this, AddNoteActivity.class);
            startActivity(intent);
        });
    }

    private void loadNotes(String query){
        noteList.clear();
        ContentResolver resolver = getContentResolver();
        Uri uri = NoteProvider.CONTENT_URI;
        String selection = null;
        String[] selectionArgs = null;

        if(!query.isEmpty()){
            selection = "title LIKE ? OR content LIKE ?";
            selectionArgs = new String[]{"%" + query + "%", "%" + query + "%"};
        }

        Cursor cursor = resolver.query(uri, null, selection, selectionArgs, null);

        if(cursor != null){
            while(cursor.moveToNext()){
                noteList.add(new Note(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("title")),
                        cursor.getString(cursor.getColumnIndexOrThrow("content"))
                ));
            }
            cursor.close();
        }

        // Show or hide "No notes" message
        if(noteList.isEmpty()){
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
        loadNotes(searchBar.getText().toString());
        adapter.notifyDataSetChanged();
    }
}
