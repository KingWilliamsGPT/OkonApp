package com.example.okonapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Note: AbsoluteLayout is deprecated and should be avoided in modern Android
 * development.
 * This is implemented specifically for the assignment requirement on Page 6.
 */
public class AbsoluteLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absolute_layout);
    }
}
