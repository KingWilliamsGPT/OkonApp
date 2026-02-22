package com.example.okonapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.exercises_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise(getString(R.string.ex_linear_layout), getString(R.string.ex_linear_layout_desc),
                LinearLayoutActivity.class));
        exercises.add(new Exercise(getString(R.string.ex_absolute_layout), getString(R.string.ex_absolute_layout_desc),
                AbsoluteLayoutActivity.class));
        exercises.add(new Exercise(getString(R.string.ex_login_personal), getString(R.string.ex_login_personal_desc),
                LoginActivity.class));
        exercises.add(new Exercise(getString(R.string.ex_controls), getString(R.string.ex_controls_desc),
                ControlsActivity.class));
        exercises.add(new Exercise(getString(R.string.ex_calculator), getString(R.string.ex_calculator_desc),
                CalculatorActivity.class));
        exercises.add(new Exercise(getString(R.string.ex_forms), getString(R.string.ex_forms_desc),
                SocialLoginActivity.class));
        exercises.add(new Exercise(getString(R.string.ex_webview), getString(R.string.ex_webview_desc),
                WebNavigatorActivity.class));
        exercises.add(new Exercise(getString(R.string.ex_todo), getString(R.string.ex_todo_desc), TodoActivity.class));
        exercises.add(new Exercise(getString(R.string.ex_location), getString(R.string.ex_location_desc),
                LocationActivity.class));

        ExerciseAdapter adapter = new ExerciseAdapter(exercises);
        recyclerView.setAdapter(adapter);
    }
}