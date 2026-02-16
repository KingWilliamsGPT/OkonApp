package com.example.touchbro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<Note> notes;
    private Context context;

    public NoteAdapter(Context context, ArrayList<Note> notes){
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Note n = notes.get(position);
        holder.tv1.setText(n.title);
        // Show a preview of content (first 50 chars)
        String preview = n.content.length() > 50 ? n.content.substring(0, 50) + "..." : n.content;
        holder.tv2.setText(preview);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NoteDetailActivity.class);
            intent.putExtra("noteId", n.id);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv1, tv2;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tv1 = itemView.findViewById(android.R.id.text1);
            tv2 = itemView.findViewById(android.R.id.text2);
        }
    }
}
