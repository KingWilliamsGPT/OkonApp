package com.example.okonapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private Cursor cursor;
    private final Context context;

    public TodoAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null)
            cursor.close();
        cursor = newCursor;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_todo, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        if (!cursor.moveToPosition(position))
            return;

        long id = cursor.getLong(cursor.getColumnIndexOrThrow(TodoDatabaseHelper.COLUMN_ID));
        String task = cursor.getString(cursor.getColumnIndexOrThrow(TodoDatabaseHelper.COLUMN_TASK));
        boolean isCompleted = cursor.getInt(cursor.getColumnIndexOrThrow(TodoDatabaseHelper.COLUMN_COMPLETED)) == 1;

        holder.tvTask.setText(task);
        holder.cbStatus.setChecked(isCompleted);

        holder.cbStatus.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put(TodoDatabaseHelper.COLUMN_COMPLETED, holder.cbStatus.isChecked() ? 1 : 0);
            context.getContentResolver().update(TodoProvider.CONTENT_URI, values, TodoDatabaseHelper.COLUMN_ID + "=?",
                    new String[] { String.valueOf(id) });
        });

        holder.btnDelete.setOnClickListener(v -> {
            context.getContentResolver().delete(TodoProvider.CONTENT_URI, TodoDatabaseHelper.COLUMN_ID + "=?",
                    new String[] { String.valueOf(id) });
        });
    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 : cursor.getCount();
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {
        CheckBox cbStatus;
        TextView tvTask;
        ImageButton btnDelete;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            cbStatus = itemView.findViewById(R.id.cb_todo_status);
            tvTask = itemView.findViewById(R.id.tv_todo_task);
            btnDelete = itemView.findViewById(R.id.btn_delete_todo);
        }
    }
}
