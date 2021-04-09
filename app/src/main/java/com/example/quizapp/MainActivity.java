package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;      // Declare EditText object for username
    Button startButton;     // Declare Button object for starting the quiz when pressed

    // Method that will be called when startButton is pressed
    public void startQuiz(View view) {
        // Show error message in Toast if the user tries to start without entering a name
        if (username.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, QuestionActivity.class);   // New intent object
            intent.putExtra("username", username.getText().toString());        // Pass the value of username EditText
            startActivity(intent);                                                   // start QuestionActivity
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startQuizButton);
        username = findViewById(R.id.usernameEditText);

        // This is to set username EditText to user's name (only available if user pressed retry quiz button)
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");
        username.setText(name);
    }
}