package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {
    // Constant for question amount
    public static final int QUESTION_AMOUNT = 5;
    TextView qTitle, qDesc, welcomeUserText, questionNumber;
    Button answer1Button, answer2Button, answer3Button, submitNextButton;
    ProgressBar questionNumberBar;
    Integer score, selectedAnswer;
    String[] questionTitles, questionDescriptions;
    String[][] answers;

    // Integers of correct answers. Values are 1-3
    int[] correctAnswer = {2, 3, 1, 1, 3};

    // Method to change the displayed question and answers
    private void changeQuestionAnswer()
    {
        qTitle.setText(questionTitles[questionNumberBar.getProgress() - 1]);
        qDesc.setText(questionDescriptions[questionNumberBar.getProgress() - 1]);

        answer1Button.setText(answers[questionNumberBar.getProgress() - 1][0]);
        answer2Button.setText(answers[questionNumberBar.getProgress() - 1][1]);
        answer3Button.setText(answers[questionNumberBar.getProgress() - 1][2]);
    }

    // Toggle the answer buttons between clickable and not clickable
    private void toggleAnswerClickable()
    {
        if (answer1Button.isClickable())
        {
            answer1Button.setClickable(false);
            answer2Button.setClickable(false);
            answer3Button.setClickable(false);
        }
        else
        {
            answer1Button.setClickable(true);
            answer2Button.setClickable(true);
            answer3Button.setClickable(true);
        }
    }

    // Highlight the correct answer by making the correct button green. Then change the submit button to Next button
    private void highlightCorrectAnswer()
    {
        switch (correctAnswer[questionNumberBar.getProgress() - 1])
        {
            case 1: answer1Button.setBackgroundColor(Color.GREEN); break;
            case 2: answer2Button.setBackgroundColor(Color.GREEN); break;
            default: answer3Button.setBackgroundColor(Color.GREEN);
        }
        selectedAnswer = 0;
        submitNextButton.setText("Next");
    }

    // Method to reset all answer buttons background color and user selected answer
    private void resetAnswerButton()
    {
        answer1Button.setBackgroundColor(Color.WHITE);
        answer2Button.setBackgroundColor(Color.WHITE);
        answer3Button.setBackgroundColor(Color.WHITE);
        selectedAnswer = -1;    // -1 on selectedAnswer means no answer is selected
    }

    // Method to highlight answer button 1 on click. Highlighted button becomes light gray
    public void selectAnswer1(View view)
    {
        answer1Button.setBackgroundColor(Color.LTGRAY);
        answer2Button.setBackgroundColor(Color.WHITE);
        answer3Button.setBackgroundColor(Color.WHITE);
        selectedAnswer = 1;
    }

    // Method to highlight answer button 2 on click. Highlighted button becomes light gray
    public void selectAnswer2(View view)
    {
        answer2Button.setBackgroundColor(Color.LTGRAY);
        answer1Button.setBackgroundColor(Color.WHITE);
        answer3Button.setBackgroundColor(Color.WHITE);
        selectedAnswer = 2;
    }

    // Method to highlight answer button 3 on click. Highlighted button becomes light gray
    public void selectAnswer3(View view)
    {
        answer3Button.setBackgroundColor(Color.LTGRAY);
        answer2Button.setBackgroundColor(Color.WHITE);
        answer1Button.setBackgroundColor(Color.WHITE);
        selectedAnswer = 3;
    }

    // Method that will be called when clicking the submit/next button
    public void submitAnswer(View view)
    {
        switch (selectedAnswer)
        {
            case 0: // This branch will run after user submitted their answer
                if (questionNumberBar.getProgress() < 5)
                {
                    questionNumberBar.setProgress(questionNumberBar.getProgress() + 1);
                    questionNumber.setText(questionNumberBar.getProgress() + "/" + questionNumberBar.getMax());
                    changeQuestionAnswer();     // Display new questions based on questionNumberBar progress
                    resetAnswerButton();        // Reset all answer buttons background color
                    toggleAnswerClickable();    // Make all answer buttons clickable again
                    submitNextButton.setText("Submit");
                }
                else // This branch runs after all 5 questions are answered
                {
                    // TODO: Create intent and send score for the next activity
                    // TODO: Create new activity (EndActivity)
                }
                break;
            case 1: // This branch will run when the user submits with answer 1 selected
                if (correctAnswer[questionNumberBar.getProgress() - 1] == 1) score++;
                toggleAnswerClickable();
                answer1Button.setBackgroundColor(Color.RED);
                highlightCorrectAnswer();
                break;
            case 2: // This branch will run when the user submits with answer 2 selected
                if (correctAnswer[questionNumberBar.getProgress() - 1] == 2) score++;
                toggleAnswerClickable();
                answer2Button.setBackgroundColor(Color.RED);
                highlightCorrectAnswer();
                break;
            case 3: // This branch will run when the user submits with answer 3 selected
                if (correctAnswer[questionNumberBar.getProgress() - 1] == 3) score++;
                toggleAnswerClickable();
                answer3Button.setBackgroundColor(Color.RED);
                highlightCorrectAnswer();
                break;
            default:    // This branch should only run when selectedAnswer is -1, which means no answer is selected
                Toast.makeText(QuestionActivity.this, "Select an answer", Toast.LENGTH_SHORT).show();
        }

        welcomeUserText.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();    // Get intent from previous activity
        String username = intent.getStringExtra("username");                                      // Get username from intent
        score = 0;                                                                                      // User score, starts at 0
        selectedAnswer = -1;                                                                            // Selected answer. -1 means no answer selected
        questionTitles = getResources().getStringArray(R.array.questionTitles);                // Get q titles
        questionDescriptions = getResources().getStringArray(R.array.questionDescriptions);    // Get q descriptions
        welcomeUserText = findViewById(R.id.welcomeUserTextView);

        // Question progress number and bar
        questionNumber = findViewById(R.id.questionProgressText);
        questionNumberBar = (ProgressBar)findViewById(R.id.questionProgressBar);
        questionNumber.setText("1/" + questionNumberBar.getMax());

        // Question title & description and buttons
        qTitle = findViewById(R.id.questionTitleText);
        qDesc = findViewById(R.id.questionDescText);
        answer1Button = (Button)findViewById(R.id.answerButton1);
        answer2Button = (Button)findViewById(R.id.answerButton2);
        answer3Button = (Button)findViewById(R.id.answerButton3);
        submitNextButton = (Button)findViewById(R.id.submitNextButton);

        // Get array of answers
        TypedArray answersArray = getResources().obtainTypedArray(R.array.answers);
        answers = new String[answersArray.length()][3];
        for (int i = 0; i < answersArray.length(); ++i)
        {
            int id = answersArray.getResourceId(i, 0);
            answers[i] = getResources().getStringArray(id);
        }
        answersArray.recycle();

        welcomeUserText.setText("Welcome " + username + "!");   // Welcome text

        // After first question, remove the welcome user text
        if (questionNumberBar.getProgress() != 1) welcomeUserText.setText("");

        // Change qTitle TextView and qDesc TextView
        qTitle.setText(questionTitles[questionNumberBar.getProgress() - 1]);
        qDesc.setText(questionDescriptions[questionNumberBar.getProgress() - 1]);

        // Change all button texts
        answer1Button.setText(answers[questionNumberBar.getProgress() - 1][0]);
        answer2Button.setText(answers[questionNumberBar.getProgress() - 1][1]);
        answer3Button.setText(answers[questionNumberBar.getProgress() - 1][2]);
        submitNextButton.setText("Submit");
    }
}