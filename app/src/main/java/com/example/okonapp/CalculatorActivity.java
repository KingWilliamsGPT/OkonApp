package com.example.okonapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        EditText etNum1 = findViewById(R.id.et_num1);
        EditText etNum2 = findViewById(R.id.et_num2);
        TextView tvResult = findViewById(R.id.tv_result);

        findViewById(R.id.btn_add).setOnClickListener(v -> calculate(etNum1, etNum2, tvResult, '+'));
        findViewById(R.id.btn_sub).setOnClickListener(v -> calculate(etNum1, etNum2, tvResult, '-'));
        findViewById(R.id.btn_mul).setOnClickListener(v -> calculate(etNum1, etNum2, tvResult, '*'));
        findViewById(R.id.btn_div).setOnClickListener(v -> calculate(etNum1, etNum2, tvResult, '/'));
    }

    private void calculate(EditText et1, EditText et2, TextView tvResult, char op) {
        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double n1 = Double.parseDouble(s1);
        double n2 = Double.parseDouble(s2);
        double res = 0;

        switch (op) {
            case '+':
                res = n1 + n2;
                break;
            case '-':
                res = n1 - n2;
                break;
            case '*':
                res = n1 * n2;
                break;
            case '/':
                if (n2 == 0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                res = n1 / n2;
                break;
        }
        tvResult.setText(String.valueOf(res));
    }
}
