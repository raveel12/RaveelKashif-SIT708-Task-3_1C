package com.example.quizandcalc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView tvScore, tvGreeting;
    Button btnNewQuiz, btnFinish;

    String userName;
    int score;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvScore = findViewById(R.id.tvScore);
        tvGreeting = findViewById(R.id.tvGreeting);
        btnNewQuiz = findViewById(R.id.btnNewQuiz);
        btnFinish = findViewById(R.id.btnFinish);


        userName = getIntent().getStringExtra("userName");
        score = getIntent().getIntExtra("score", 0);

        tvGreeting.setText("Well done, " + userName + "!");
        tvScore.setText("Your Score: " + score);

        btnNewQuiz.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
            finish();
        });

        btnFinish.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }
}