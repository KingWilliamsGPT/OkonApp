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

public class NoteDetailActivity extends AppCompatActivity {

    TextView tvTitle, tvContent;
    Button btnEdit;
    int noteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        setTitle("Note Details");

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvContent = findViewById(R.id.tvDetailContent);
        btnEdit = findViewById(R.id.btnEditNote);

        noteId = getIntent().getIntExtra("noteId", -1);
        loadNote();

        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(NoteDetailActivity.this, AddNoteActivity.class);
            intent.putExtra("noteId", noteId);
            startActivity(intent);
        });
    }

    private void loadNote() {
        if (noteId != -1) {
            ContentResolver resolver = getContentResolver();
            Uri noteUri = ContentUris.withAppendedId(NoteProvider.CONTENT_URI, noteId);
            Cursor cursor = resolver.query(noteUri, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                tvTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow("title")));
                tvContent.setText(cursor.getString(cursor.getColumnIndexOrThrow("content")));
                cursor.close();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNote();
    }
}
