package pl.org.ponton.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.org.ponton.levels.Level1;
import pl.ponton.R;

public class MainActivity extends AppCompatActivity {

    private Button poziom1Button;

    private Button poziom2Button;

    private Button poziom3Button;

    private Intent intent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();

        poziom1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Level1 level1 = Level1.getInstance();
                level1.loadLevel();

                intent = new Intent(MainActivity.this, QuestionActivity.class);

                startActivity(intent);
            }
        });

        poziom2Button.setClickable(false);
        poziom3Button.setClickable(false);
    }

    private void initButtons() {
        poziom1Button = findViewById(R.id.poziom1);
        poziom2Button = findViewById(R.id.poziom2);
        poziom3Button = findViewById(R.id.poziom3);
    }
}
