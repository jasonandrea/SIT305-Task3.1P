package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {
    // Constant for question amount
    public static final int QUESTION_AMOUNT = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();                                        // Get intent from previous activity
        TextView welcomeUserText = findViewById(R.id.welcomeUserTextView);
        String username = intent.getStringExtra("username");          // Get username from intent
        Integer questionProgress = 0;                                       // Progress counter, starts at 0

        welcomeUserText.setText("Welcome " + username + "!");               // Welcome text

        // Loop for QUESTION_AMOUNT (the amount of quiz questions) times
        for (int i = 0; i < QUESTION_AMOUNT; i++)
        {
            // TODO: Loop questions
        }
    }
}