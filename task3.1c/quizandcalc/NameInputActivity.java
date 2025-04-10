package com.example.quizandcalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NameInputActivity extends AppCompatActivity {

    EditText etName;
    Button btnStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_input);

        etName = findViewById(R.id.etName);
        btnStartQuiz = findViewById(R.id.btnStartQuiz);

        SharedPreferences prefs = getSharedPreferences("quizApp", MODE_PRIVATE);
        String savedName = prefs.getString("userName", "");
        etName.setText(savedName);

        btnStartQuiz.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (!name.isEmpty()) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("userName", name);
                editor.apply();

                Intent intent = new Intent(NameInputActivity.this, QuizActivity.class);
                intent.putExtra("userName", name);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
