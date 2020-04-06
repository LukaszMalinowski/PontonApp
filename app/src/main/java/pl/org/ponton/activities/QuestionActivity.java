package pl.org.ponton.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Context context = getApplicationContext();

        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        editor = preferences.edit();

        buttonList = new ArrayList<>();

        level = Level.getInstance();

        System.out.println(level);

        try {
            question = level.getQuestion();
        } catch (Exception e) {
            editor.putInt("score", User.getUser().getScore());
            editor.commit();
            onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        initButtons();

        initQuestionText();

        addButtonListners();
    }

    private void addButtonListners() {
        for (final AnswerButton button : buttonList) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(button.isCorrect()) {
                        User.getUser().setScore(User.getUser().getScore() + 100);
                        Snackbar.make(v, "Odpowiedź prawidłowa.", Snackbar.LENGTH_INDEFINITE)
                                .setAction("kliknij tu", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                        startActivity(getIntent());
                                    }
                                })
                                .show();
                    }
                    else {
                        User.getUser().setScore(User.getUser().getScore() - 100);
                        Snackbar.make(v, "Odpowiedź nieprawidłowa.", Snackbar.LENGTH_INDEFINITE)
                                .setAction("kliknij tu", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                        startActivity(getIntent());
                                    }
                                })
                                .show();
                    }

                    for (AnswerButton tempButton : buttonList) {
                        tempButton.setClickable(false);
                    }
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
