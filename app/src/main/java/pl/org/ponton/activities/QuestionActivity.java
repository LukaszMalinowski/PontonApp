package pl.org.ponton.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.org.ponton.exceptions.NoMoreQuestionsException;
import pl.org.ponton.exceptions.NoMoreWrongQuestionsException;
import pl.org.ponton.user.User;
import pl.ponton.R;
import pl.org.ponton.levels.Level;
import pl.org.ponton.questions.AnswerButton;
import pl.org.ponton.questions.QuestionWrapper;

public class QuestionActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "settingsPreferences";

    private SharedPreferences preferences;

    private SharedPreferences.Editor editor;

    private List<AnswerButton> buttonList;

    private Level level;

    private QuestionWrapper question;

    private Button confirmButton;

    private boolean addQuestions = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Context context = getApplicationContext();

        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        editor = preferences.edit();

        buttonList = new ArrayList<>();

        confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setVisibility(View.INVISIBLE);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmActionHandler(v);
            }
        });

        level = Level.getInstance();

        try {
            question = level.getQuestion();
        } catch (NoMoreQuestionsException e) {
            try {
                addQuestions = false;
                question = level.getWrongQuestions();
            } catch (NoMoreWrongQuestionsException ex) {
                editor.putInt("score", User.getUser().getScore());
                editor.commit();
                onBackPressed();
            }
        }
    }

    private void confirmActionHandler(View v) {
        for (AnswerButton button : buttonList) {
            confirmButton.setClickable(false);

            for (AnswerButton tempButton : buttonList) {
                tempButton.setClickable(false);

                if(button.isCorrect())
                    button.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            }

            if(!button.isChecked())
                continue;

            if(button.isCorrect()) {
                User.getUser().setScore(User.getUser().getScore() + 100);
                Snackbar.make(v, "Odpowiedź prawidłowa.", Snackbar.LENGTH_INDEFINITE)
                        .setAction("dalej", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .show();
            }
            else {
//                User.getUser().setScore(User.getUser().getScore() - 100);
                button.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                if(addQuestions)
                    level.addWrongQuestion(question);
                Snackbar.make(v, "Odpowiedź nieprawidłowa.", Snackbar.LENGTH_INDEFINITE)
                        .setAction("dalej", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .show();
            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initButtons();

        initQuestionText();

        addButtonListeners();
    }

    private void addButtonListeners() {
        for (final AnswerButton button : buttonList) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (AnswerButton tempButton : buttonList) {
                        tempButton.setChecked(false);
                        tempButton.getBackground().clearColorFilter();
                    }
                    button.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                    button.setChecked(true);
                    confirmButton.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    private void initQuestionText() {
        TextView questionTextView = findViewById(R.id.questionTextView);

        questionTextView.setText(question.getQuestion());
    }

    private void initButtons() {
        buttonList.add((AnswerButton) findViewById(R.id.buttonA));
        buttonList.add((AnswerButton) findViewById(R.id.buttonB));
        buttonList.add((AnswerButton) findViewById(R.id.buttonC));
        buttonList.add((AnswerButton) findViewById(R.id.buttonD));

        List<AnswerButton> tempList = new ArrayList<>();

        tempList.add(buttonList.get(0));
        tempList.add(buttonList.get(1));
        tempList.add(buttonList.get(2));
        tempList.add(buttonList.get(3));

        Random random = new Random();

        ArrayList<String> tempAnswers = new ArrayList();

        tempAnswers.addAll(question.getAnswers());

        while (!tempList.isEmpty()) {

            int randomIndex = random.nextInt(tempList.size());

            AnswerButton button = tempList.get(randomIndex);

            tempList.remove(randomIndex);

            randomIndex = random.nextInt(tempAnswers.size());
            String answer = tempAnswers.get(randomIndex);
            tempAnswers.remove(randomIndex);

            if(answer.startsWith("T"))
                button.setCorrect(true);

            answer = answer.substring(1);

            button.setText(answer);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QuestionActivity.this, SelectLevelActivity.class);
        QuestionActivity.this.startActivity(intent);
        finish();
    }
}
