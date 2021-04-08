package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText username;      // Declare EditText object for username
    Button startButton;     // Declare Button object for starting the quiz when pressed

    // Method that will be called when startButton is pressed
    public void startQuiz(View view) {
        Intent intent = new Intent(this, QuestionActivity.class);   // New intent object
        intent.putExtra("username", username.getText().toString());        // Pass the value of username EditText
        startActivity(intent);                                                   // start QuestionActivity
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startQuizButton);
        username = findViewById(R.id.usernameEditText);
    }
}