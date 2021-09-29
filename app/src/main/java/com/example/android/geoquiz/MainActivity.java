package com.example.android.geoquiz;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText firstTextResponse, fourthTextResponse;
    private RadioGroup secondQuestionGroup, fifthQuestionGroup, seventhQuestionGroup;
    private RadioButton correctSecondAnswer, correctFifthAnswer, correctSeventhAnswer;
    private String secondAnswer, fifthAnswer, seventhAnswer;
    private CheckBox governorBox, senatorBox, professorBox, noneAboveBox, franciscoPizarroBox, mocheBox, portugueseBox, spanishBox;
    private TextView explanationOne, explanationTwo, explanationThree, explanationFour, explanationFive, explanationSix, explanationSeven;
    private Button submitButton, tryAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstTextResponse = findViewById(R.id.firstQuestionText);
        fourthTextResponse = findViewById(R.id.fourthQuestionText);

        secondQuestionGroup = findViewById(R.id.secondQuestionGroup);
        fifthQuestionGroup = findViewById(R.id.fifthQuestionGroup);
        seventhQuestionGroup = findViewById(R.id.seventhQuestionGroup);

        correctSecondAnswer = findViewById(R.id.hiram_bingham_button);
        correctFifthAnswer = findViewById(R.id.quechua_button);
        correctSeventhAnswer = findViewById(R.id.royal_retreat_button);

        governorBox = findViewById(R.id.governor_box);
        senatorBox = findViewById(R.id.senator_box);
        professorBox = findViewById(R.id.professor_box);
        noneAboveBox = findViewById(R.id.none_above_box);
        franciscoPizarroBox = findViewById(R.id.francisco_pizarro_box);
        mocheBox = findViewById(R.id.moche_box);
        portugueseBox = findViewById(R.id.portuguese_box);
        spanishBox = findViewById(R.id.spanish_box);

        explanationOne = findViewById(R.id.question_one_explanation);
        explanationTwo = findViewById(R.id.question_two_explanation);
        explanationThree = findViewById(R.id.question_three_explanation);
        explanationFour = findViewById(R.id.question_four_explanation);
        explanationFive = findViewById(R.id.question_five_explanation);
        explanationSix = findViewById(R.id.question_six_explanation);
        explanationSeven = findViewById(R.id.question_seven_explanation);

        submitButton = findViewById(R.id.submit_button);
        tryAgainButton = findViewById(R.id.try_again_button);
    }

    /**
     * Receives answers on the radiobutton questions.
     *
     * @param v RadioButtons.
     */
    public void secondQuestionAnswer(View v) {
        int secondGroupId = secondQuestionGroup.getCheckedRadioButtonId();
        RadioButton secondGroupChecked = findViewById(secondGroupId);
        secondAnswer = secondGroupChecked.getText().toString();
    }

    public void fifthQuestionAnswer(View v) {
        int fifthGroupId = fifthQuestionGroup.getCheckedRadioButtonId();
        RadioButton fifthGroupChecked = findViewById(fifthGroupId);
        fifthAnswer = fifthGroupChecked.getText().toString();
    }

    public void seventhQuestionAnswer(View v) {
        int seventhGroupId = seventhQuestionGroup.getCheckedRadioButtonId();
        RadioButton seventhGroupChecked = findViewById(seventhGroupId);
        seventhAnswer = seventhGroupChecked.getText().toString();
    }

    /**
     * Does the grading.
     * Submits answers and shows a Toast message.
     *
     * @param v Submit button.
     */
    public void submitAnswers(View v) {
        int score = 0;
        // Capturing user text responses.
        String firstAnswer = firstTextResponse.getText().toString().toLowerCase().trim();
        String fourthAnswer = fourthTextResponse.getText().toString().toLowerCase().trim();
        // Grading all answers.
        if (firstAnswer.equalsIgnoreCase("machu picchu")) score++;

        if (secondAnswer != null && secondAnswer.equalsIgnoreCase(getString(R.string.hiram_bingham)))
            score++;

        // Grading the third answer.
        if (governorBox.isChecked() && senatorBox.isChecked() && professorBox.isChecked() && !noneAboveBox.isChecked())
            score++;

        if (fourthAnswer.equalsIgnoreCase("inca") || fourthAnswer.equalsIgnoreCase("inca tribe"))
            score++;

        if (fifthAnswer != null && fifthAnswer.equalsIgnoreCase(getString(R.string.quechua)))
            score++;

        // Grading the sixth answer.
        if (franciscoPizarroBox.isChecked() && !mocheBox.isChecked() && !portugueseBox.isChecked() && spanishBox.isChecked())
            score++;

        if (seventhAnswer != null && seventhAnswer.equalsIgnoreCase(getString(R.string.royal_retreat)))
            score++;


        // Submitting answers.
        String toastMessage;

        if (score > 0) {
            toastMessage = "\t\t\t\t\t\tYour score is " + score + "/7.\nHope you learned something knew.";

            // Making Try again button visible and Submit button gone.
            tryAgainButton.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.GONE);

            firstTextResponse.clearFocus();
            fourthTextResponse.clearFocus();

            // Highlighting correct answers
            correctSecondAnswer.setTextColor(Color.GREEN);
            correctFifthAnswer.setTextColor(Color.GREEN);
            correctSeventhAnswer.setTextColor(Color.GREEN);
            governorBox.setTextColor(Color.GREEN);
            senatorBox.setTextColor(Color.GREEN);
            professorBox.setTextColor(Color.GREEN);
            franciscoPizarroBox.setTextColor(Color.GREEN);
            spanishBox.setTextColor(Color.GREEN);

            // Showing explanation.
            explanationOne.setVisibility(View.VISIBLE);
            explanationTwo.setVisibility(View.VISIBLE);
            explanationThree.setVisibility(View.VISIBLE);
            explanationFour.setVisibility(View.VISIBLE);
            explanationFive.setVisibility(View.VISIBLE);
            explanationSix.setVisibility(View.VISIBLE);
            explanationSeven.setVisibility(View.VISIBLE);
        } else {
            toastMessage = "You have 0 right answers, try again";

            // Clear variables.
            secondAnswer = null;
            fifthAnswer = null;
            seventhAnswer = null;
            // Clear text fields.
            firstTextResponse.setText(null);
            firstTextResponse.clearFocus();
            fourthTextResponse.setText(null);
            fourthTextResponse.clearFocus();
            // Uncheck RadioButtons.
            int secondGroupId = secondQuestionGroup.getCheckedRadioButtonId();
            RadioButton secondGroupChecked = findViewById(secondGroupId);
            if (secondGroupChecked != null) secondGroupChecked.setChecked(false);
            int fifthGroupId = fifthQuestionGroup.getCheckedRadioButtonId();
            RadioButton fifthGroupChecked = findViewById(fifthGroupId);
            if (fifthGroupChecked != null) fifthGroupChecked.setChecked(false);
            int seventhGroupId = seventhQuestionGroup.getCheckedRadioButtonId();
            RadioButton seventhGroupChecked = findViewById(seventhGroupId);
            if (seventhGroupChecked != null) seventhGroupChecked.setChecked(false);
            // Uncheck Boxes.
            governorBox.setChecked(false);
            senatorBox.setChecked(false);
            professorBox.setChecked(false);
            noneAboveBox.setChecked(false);
            franciscoPizarroBox.setChecked(false);
            mocheBox.setChecked(false);
            portugueseBox.setChecked(false);
            spanishBox.setChecked(false);

            ScrollView scrollView = findViewById(R.id.main_scroll_view);
            scrollView.scrollTo(0, 0);
        }

        ScrollView scrollView = findViewById(R.id.main_scroll_view);
        scrollView.scrollTo(0, 0);

        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }

    /**
     * Starts over.
     * Clears quiz.
     *
     * @param v Try again button.
     */
    public void resetQuiz(View v) {
        // Making Try again button gone and Submit button visible.
        tryAgainButton.setVisibility(View.GONE);
        submitButton.setVisibility(View.VISIBLE);

        // Hiding correct answers
        correctSecondAnswer.setTextColor(Color.BLACK);
        correctFifthAnswer.setTextColor(Color.BLACK);
        correctSeventhAnswer.setTextColor(Color.BLACK);
        governorBox.setTextColor(Color.BLACK);
        senatorBox.setTextColor(Color.BLACK);
        professorBox.setTextColor(Color.BLACK);
        franciscoPizarroBox.setTextColor(Color.BLACK);
        spanishBox.setTextColor(Color.BLACK);

        // Hiding explanation.
        explanationOne.setVisibility(View.GONE);
        explanationTwo.setVisibility(View.GONE);
        explanationThree.setVisibility(View.GONE);
        explanationFour.setVisibility(View.GONE);
        explanationFive.setVisibility(View.GONE);
        explanationSix.setVisibility(View.GONE);
        explanationSeven.setVisibility(View.GONE);

        // Clear variables.
        secondAnswer = null;
        fifthAnswer = null;
        seventhAnswer = null;
        // Clear text fields.
        firstTextResponse.setText(null);
        firstTextResponse.clearFocus();
        fourthTextResponse.setText(null);
        fourthTextResponse.clearFocus();
        // Uncheck RadioButtons.
        int secondGroupId = secondQuestionGroup.getCheckedRadioButtonId();
        RadioButton secondGroupChecked = findViewById(secondGroupId);
        if (secondGroupChecked != null) secondGroupChecked.setChecked(false);
        int fifthGroupId = fifthQuestionGroup.getCheckedRadioButtonId();
        RadioButton fifthGroupChecked = findViewById(fifthGroupId);
        if (fifthGroupChecked != null) fifthGroupChecked.setChecked(false);
        int seventhGroupId = seventhQuestionGroup.getCheckedRadioButtonId();
        RadioButton seventhGroupChecked = findViewById(seventhGroupId);
        if (seventhGroupChecked != null) seventhGroupChecked.setChecked(false);
        // Uncheck Boxes.
        governorBox.setChecked(false);
        senatorBox.setChecked(false);
        professorBox.setChecked(false);
        noneAboveBox.setChecked(false);
        franciscoPizarroBox.setChecked(false);
        mocheBox.setChecked(false);
        portugueseBox.setChecked(false);
        spanishBox.setChecked(false);

        ScrollView scrollView = findViewById(R.id.main_scroll_view);
        scrollView.scrollTo(0, 0);

        Toast.makeText(this, "Reset.", Toast.LENGTH_SHORT).show();
    }
}