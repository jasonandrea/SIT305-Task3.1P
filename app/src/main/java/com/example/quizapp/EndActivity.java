package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
    String username;

    // Method to close the app. Called on clicking finish button
    public void closeApp(View view) {
        finish();   // Close this activity
    }

    // Method to retry the quiz. Called on clicking Try New Quiz button. EditText will automatically be filled with user's name
    public void retryQuiz(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Button retryQuizButton = (Button)findViewById(R.id.retryButton);
        Button finishButton = (Button)findViewById(R.id.finishButton);
        TextView congratsText = findViewById(R.id.congratsTextView);
        TextView userScore = findViewById(R.id.userScoreTextView);
        Intent intent = getIntent();
        String score = intent.getStringExtra("userScore");
        username = intent.getStringExtra("username");

        congratsText.setText("Congratulations " + username + "!");
        userScore.setText(score);
    }
}