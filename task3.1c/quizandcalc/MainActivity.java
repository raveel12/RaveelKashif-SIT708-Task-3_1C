package com.example.quizandcalc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnCalculator, btnQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalculator = findViewById(R.id.btnCalculator);
        btnQuiz = findViewById(R.id.btnQuiz);

        btnQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NameInputActivity.class);
            startActivity(intent);
        });

        btnCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });
    }
}
