package pl.org.ponton.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.org.ponton.levels.Level;
import pl.org.ponton.user.User;
import pl.ponton.R;

public class SelectLevelActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "settingsPreferences";

    private SharedPreferences preferences;

    private Button level1Button;

    private Button level2Button;

    private Button level3Button;

    private TextView scoreTextView;

    private Intent intent;

    private TextView level2Text;

    private TextView level3Text;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        Context context = getApplicationContext();

        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        User.loadUser(preferences.getInt("score", 0));

        initScoreText();

        initButtons();

        initScoreLeftText();

        scoreTextView.setText("Wynik: " + User.getUser().getScore());

        level1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level.getInstance().loadLevel(Level.LevelType.LEVEL1);

                intent = new Intent(SelectLevelActivity.this, QuestionActivity.class);

                startActivity(intent);
            }
        });

        level2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level.getInstance().loadLevel(Level.LevelType.LEVEL2);

                intent = new Intent(SelectLevelActivity.this, QuestionActivity.class);

                startActivity(intent);
            }
        });

        level3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level.getInstance().loadLevel(Level.LevelType.LEVEL3);

                intent = new Intent(SelectLevelActivity.this, QuestionActivity.class);

                startActivity(intent);
            }
        });

        if (User.getUser().getScore() < 400) {
            level2Button.setClickable(false);
            level2Text.setText("Poziom zablokowany. Brakuje " + (400 - User.getUser().getScore()) + " punktów");
        }


        if (User.getUser().getScore() < 800) {
            level3Button.setClickable(false);
            level3Text.setText("Poziom zablokowany. Brakuje " + (800 - User.getUser().getScore()) + " punktów");
        }
    }

    private void initScoreLeftText() {
        level2Text = findViewById(R.id.level2Text);
        level3Text = findViewById(R.id.level3Text);
    }

    private void initScoreText() {
        scoreTextView = findViewById(R.id.score);
    }

    private void initButtons() {
        level1Button = findViewById(R.id.level1);
        level2Button = findViewById(R.id.level2);
        level3Button = findViewById(R.id.level3);
    }
}
