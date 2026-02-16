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

public class AddNoteActivity extends AppCompatActivity {

    EditText etTitle, etContent;
    Button btnSubmit;
    int editNoteId = -1; // -1 means adding, otherwise editing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = findViewById(R.id.etNoteTitle);
        etContent = findViewById(R.id.etNoteContent);
        btnSubmit = findViewById(R.id.btnSubmitNote);

        // Check if we are editing an existing note
        editNoteId = getIntent().getIntExtra("noteId", -1);
        if (editNoteId != -1) {
            setTitle("Edit Note");
            btnSubmit.setText("Update Note");
            loadNoteData(editNoteId);
        } else {
            setTitle("Add Note");
        }

        btnSubmit.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentResolver resolver = getContentResolver();
            ContentValues cv = new ContentValues();
            cv.put("title", title);
            cv.put("content", content);

            if (editNoteId != -1) {
                // Update existing note via ContentProvider
                Uri noteUri = ContentUris.withAppendedId(NoteProvider.CONTENT_URI, editNoteId);
                int rows = resolver.update(noteUri, cv, null, null);
                if (rows > 0) {
                    Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to update note", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Insert new note via ContentProvider
                Uri result = resolver.insert(NoteProvider.CONTENT_URI, cv);
                if (result != null) {
                    Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Failed to add note", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadNoteData(int noteId) {
        ContentResolver resolver = getContentResolver();
        Uri noteUri = ContentUris.withAppendedId(NoteProvider.CONTENT_URI, noteId);
        Cursor cursor = resolver.query(noteUri, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            etTitle.setText(cursor.getString(cursor.getColumnIndexOrThrow("title")));
            etContent.setText(cursor.getString(cursor.getColumnIndexOrThrow("content")));
            cursor.close();
        }
    }
}
