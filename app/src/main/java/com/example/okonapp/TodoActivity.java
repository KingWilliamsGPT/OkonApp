package com.example.okonapp;

import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TodoActivity extends AppCompatActivity {

    private TodoAdapter adapter;
    private ContentObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        RecyclerView recyclerView = findViewById(R.id.rv_todos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = getContentResolver().query(TodoProvider.CONTENT_URI, null, null, null, null);
        adapter = new TodoAdapter(this, cursor);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_todo);
        fab.setOnClickListener(v -> showAddTodoDialog());

        // Observe changes from ContentProvider
        observer = new ContentObserver(new Handler(Looper.getMainLooper())) {
            @Override
            public void onChange(boolean selfChange) {
                refreshData();
            }
        };
        getContentResolver().registerContentObserver(TodoProvider.CONTENT_URI, true, observer);
    }

    private void refreshData() {
        Cursor cursor = getContentResolver().query(TodoProvider.CONTENT_URI, null, null, null, null);
        adapter.swapCursor(cursor);
    }

    private void showAddTodoDialog() {
        final EditText taskEditText = new EditText(this);
        taskEditText.setHint("Enter task name");

        new AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setView(taskEditText)
                .setPositiveButton("Add", (dialog, which) -> {
                    String task = taskEditText.getText().toString();
                    if (!task.isEmpty()) {
                        ContentValues values = new ContentValues();
                        values.put(TodoDatabaseHelper.COLUMN_TASK, task);
                        getContentResolver().insert(TodoProvider.CONTENT_URI, values);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getContentResolver().unregisterContentObserver(observer);
    }
}
