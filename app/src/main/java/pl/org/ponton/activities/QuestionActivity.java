package pl.org.ponton.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.ponton.R;
import pl.org.ponton.levels.Level1;
import pl.org.ponton.questions.AnswerButton;
import pl.org.ponton.questions.QuestionWrapper;

public class QuestionActivity extends AppCompatActivity {

    private List<AnswerButton> buttonList;

    private Level1 level1;

    private QuestionWrapper question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        buttonList = new ArrayList<>();

        level1 = Level1.getInstance();

        try {
            question = level1.getQuestion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initButtons();

        initQuestionText();
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
}
