package pl.org.ponton.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.org.ponton.user.User;
import pl.ponton.R;

public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "settingsPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = findViewById(R.id.startButton);

        Context context = getApplicationContext();

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        User.loadUser(preferences.getInt("score", 0));

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectLevelActivity.class);
                startActivity(intent);
            }
        });
    }
}
