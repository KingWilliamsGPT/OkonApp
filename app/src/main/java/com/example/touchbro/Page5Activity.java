package com.example.touchbro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Page5Activity extends AppCompatActivity implements View.OnClickListener {

    TextView tvDisplay;
    String currentInput = "";
    String operator = "";
    double firstNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);

        tvDisplay = findViewById(R.id.tvDisplay);

        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnDot, R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply,
                R.id.btnDivide, R.id.btnEquals, R.id.btnClear, R.id.btnDelete
        };

        for (int id : buttonIds) {
            Button b = findViewById(id);
            b.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        // Number buttons + dot
        if (id >= R.id.btn0 && id <= R.id.btn9) {
            currentInput += ((Button)view).getText().toString();
            tvDisplay.setText(currentInput);
            return;
        }
        if (id == R.id.btnDot) {
            if (!currentInput.contains(".")) currentInput += ".";
            tvDisplay.setText(currentInput);
            return;
        }

        // Operators
        if (id == R.id.btnPlus || id == R.id.btnMinus || id == R.id.btnMultiply || id == R.id.btnDivide) {
            if(!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                operator = ((Button)view).getText().toString();
                currentInput = "";
            }
            return;
        }

        // Equals
        if (id == R.id.btnEquals) {
            if(!currentInput.isEmpty() && !operator.isEmpty()) {
                double secondNumber = Double.parseDouble(currentInput);
                double result = 0;

                switch(operator) {
                    case "+": result = firstNumber + secondNumber; break;
                    case "−": result = firstNumber - secondNumber; break;
                    case "×": result = firstNumber * secondNumber; break;
                    case "÷": result = firstNumber / secondNumber; break;
                }

                tvDisplay.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                operator = "";
            }
            return;
        }

        // Clear
        if (id == R.id.btnClear) {
            currentInput = "";
            operator = "";
            firstNumber = 0;
            tvDisplay.setText("0");
            return;
        }

        // Delete
        if (id == R.id.btnDelete) {
            if(!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                tvDisplay.setText(currentInput.isEmpty() ? "0" : currentInput);
            }
        }
    }
}
