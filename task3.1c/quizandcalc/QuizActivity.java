package com.example.quizandcalc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    TextView tvQuestion, tvQuestionCount;
    RadioGroup rgOptions;
    ProgressBar progressBar;
    Button btnSubmit;

    int currentQuestion = 0;
    int score = 0;
    boolean answered = false;

    String userName;
    List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionCount = findViewById(R.id.tvQuestionCount);
        rgOptions = findViewById(R.id.rgOptions);
        progressBar = findViewById(R.id.progressBar);
        btnSubmit = findViewById(R.id.btnSubmit);

        userName = getIntent().getStringExtra("userName");
        questionList = getQuestions();

        showQuestion();

        btnSubmit.setOnClickListener(v -> {
            if (!answered) {
                int selectedId = rgOptions.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                answered = true;

                RadioButton selected = findViewById(selectedId);
                int index = rgOptions.indexOfChild(selected);
                int correctIndex = questionList.get(currentQuestion).correctAnswerIndex;

                if (index == correctIndex) {
                    selected.setBackgroundColor(Color.GREEN);
                    score++;
                } else {
                    selected.setBackgroundColor(Color.RED);
                    RadioButton correct = (RadioButton) rgOptions.getChildAt(correctIndex);
                    correct.setBackgroundColor(Color.GREEN);
                }

                for (int i = 0; i < rgOptions.getChildCount(); i++) {
                    rgOptions.getChildAt(i).setEnabled(false);
                }

                btnSubmit.setText(currentQuestion == questionList.size() - 1 ? "Finish" : "Next");

            }
            else {
                currentQuestion++;
                if (currentQuestion < questionList.size()) {
                    showQuestion();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    void showQuestion() {
        answered = false;

        Question q = questionList.get(currentQuestion);
        tvQuestion.setText(q.questionText);
        tvQuestionCount.setText((currentQuestion + 1) + "/" + questionList.size());
        progressBar.setProgress(currentQuestion + 1);

        rgOptions.removeAllViews();
        for (String opt : q.options) {
            RadioButton rb = new RadioButton(this);
            rb.setText(opt);
            rb.setPadding(8, 16, 8, 16);
            rb.setEnabled(true);
            rb.setBackgroundColor(Color.TRANSPARENT);
            rgOptions.addView(rb);
        }

        btnSubmit.setText("Submit");
    }

    List<Question> getQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("HTML is a programing language used to create?", new String[]{"Web pages", "Mobile app", "cloud app"}, 0));
        list.add(new Question("Which language is used in Android?", new String[]{"C++", "Java", "Python"}, 1));
        list.add(new Question("What can be used to develop mobile applications?", new String[]{"Android studio", "Jenkins", "Google colab"}, 0));
        return list;
    }
}

class Question {
    String questionText;
    String[] options;
    int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}
