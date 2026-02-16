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

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<Student> students;
    private Context context;

    public StudentAdapter(Context context, ArrayList<Student> students){
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Student s = students.get(position);
        holder.tv1.setText(s.name);
        holder.tv2.setText(s.matricNo);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StudentDetailActivity.class);
            intent.putExtra("studentId", s.id);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount(){
        return students.size();
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
