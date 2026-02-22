package com.example.okonapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private final List<Exercise> exercises;

    public ExerciseAdapter(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.titleTextView.setText(exercise.getTitle());
        holder.descriptionTextView.setText(exercise.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Context context = v.getContext();
            try {
                if (exercise.getActivityClass() != null) {
                    Intent intent = new Intent(context, exercise.getActivityClass());
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Exercise implementation coming soon!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("ExerciseAdapter", "Error starting activity", e);
                Toast.makeText(context, "Error starting exercise activity", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView titleTextView;
        public final TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.exercise_title);
            descriptionTextView = itemView.findViewById(R.id.exercise_description);
        }
    }
}
