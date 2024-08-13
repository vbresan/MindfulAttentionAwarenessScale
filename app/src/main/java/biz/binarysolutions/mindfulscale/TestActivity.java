package biz.binarysolutions.mindfulscale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class TestActivity extends AppCompatActivity {

    private static final int[] statements = {
        R.string.statement01,
        R.string.statement02,
        R.string.statement03,
        R.string.statement04,
        R.string.statement05,
        R.string.statement06,
        R.string.statement07,
        R.string.statement08,
        R.string.statement09,
        R.string.statement10,
        R.string.statement11,
        R.string.statement12,
        R.string.statement13,
        R.string.statement14,
        R.string.statement15
    };

    private static int[] scores =
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

    private int index = 0;

    /**
     *
     */
    private void displayStatement() {

        TextView view = findViewById(R.id.textViewStatement);
        if (view == null) {
            return;
        }

        view.setText(statements[index]);
    }

    /**
     *
     */
    private void displayProgress() {

        TextView view = findViewById(R.id.textViewProgress);
        if (view == null) {
            return;
        }

        view.setText((index + 1) + "/15");
    }

    /**
     *
     */
    private void showNextStatement() {

        Intent intent = new Intent(this, TestActivity.class);
        String key    = getString(R.string.extra_key_test_index);
        intent.putExtra(key, index + 1);

        startActivity(intent);
    }

    /**
     *
     * @return
     */
    private float getAverage() {

        float sum = 0;

        for (int score : scores) {
            sum += score;
        }

        return sum / scores.length;
    }

    /**
     *
     */
    private void showResult() {

        String key   = getString(R.string.extra_key_score);
        float  value = getAverage();

        SharedPreferences preferences =
            getApplicationContext().getSharedPreferences("score", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        editor.commit();

        startActivity(new Intent(this, ScoreActivity.class));
    }

    /**
     *
     */
    private void enableNextButton() {

        Button view = findViewById(R.id.buttonNext);
        if (view == null) {
            return;
        }

        view.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        String key    = getString(R.string.extra_key_test_index);
        index = intent.getIntExtra(key, 0);

        displayStatement();
        displayProgress();
    }

    /**
     *
     * @param v
     */
    public void onNextClicked(View v) {

        if (index < 14) {
            showNextStatement();
        } else {
            showResult();
        }
    }

    /**
     *
     * @param view
     */
    public void onRadioButtonClicked(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.radio1:
                scores[index] = 1;
                break;
            case R.id.radio2:
                scores[index] = 2;
                break;
            case R.id.radio3:
                scores[index] = 3;
                break;
            case R.id.radio4:
                scores[index] = 4;
                break;
            case R.id.radio5:
                scores[index] = 5;
                break;
            case R.id.radio6:
                scores[index] = 6;
                break;
        }

        enableNextButton();
    }
}