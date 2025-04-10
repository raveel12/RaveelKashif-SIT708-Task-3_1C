package com.example.quizandcalc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    EditText etValue1, etValue2;
    Button btnAdd, btnSubtract, btnFinish;
    TextView tvResult;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        etValue1 = findViewById(R.id.etValue1);
        etValue2 = findViewById(R.id.etValue2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        tvResult = findViewById(R.id.tvResult);
        btnFinish = findViewById(R.id.btnFinish);

        btnAdd.setOnClickListener(v -> performCalculation(true));
        btnSubtract.setOnClickListener(v -> performCalculation(false));
        btnFinish.setOnClickListener(v -> {
            Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    void performCalculation(boolean isAddition) {
        String val1Str = etValue1.getText().toString().trim();
        String val2Str = etValue2.getText().toString().trim();

        if (val1Str.isEmpty() || val2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both values", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int num1 = Integer.parseInt(val1Str);
            int num2 = Integer.parseInt(val2Str);
            int result = isAddition ? num1 + num2 : num1 - num2;

            tvResult.setText("Result: " + result);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }
}
