package biz.binarysolutions.mindfulscale;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

/**
 *
 */
public class ScoreActivity extends AppCompatActivity {

    private static final String PACKAGE_NAME =
        "biz.binarysolutions.mindfulnessmeditation";

    private float score = 0.0f;

    /**
     *
     * @param packageManager
     * @return
     */
    private boolean isPackageInstalled(PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(PACKAGE_NAME, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     *
     */
    private void displayScore() {

        TextView view = findViewById(R.id.textViewScore);
        if (view == null) {
            return;
        }

        String text = String.format(Locale.getDefault(), "%.2f", score);
        view.setText(text);
    }

    /**
     *
     */
    private void displayProgress() {

        ProgressBar view = findViewById(R.id.progressBar);
        if (view == null) {
            return;
        }

        int progress = (int) (score * 100 / 6);
        view.setProgress(progress);

        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(view, "progress", 0, progress);
        progressAnimator.setDuration(750);
        progressAnimator.start();
    }

    /**
     *
     */
    private void displayMeditationAppLink() {

        View view = findViewById(R.id.linearLayoutMeditationApp);
        if (view == null) {
            return;
        }

        boolean isInstalled = isPackageInstalled(getPackageManager());
        int     visibility  = isInstalled? View.INVISIBLE : View.VISIBLE;

        view.setVisibility(visibility);
    }

    /**
     *
     * @param view
     */
    private void displayAboutScore(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
            .setTitle(R.string.about_score)
            .setMessage(R.string.description)
            .setPositiveButton(android.R.string.ok, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     *
     * @param view
     */
    private void retakeTest(View view) {
        startActivity(new Intent(this, PretestActivity.class));
    }

    /**
     *
     * @param view
     */
    private void learnMore(View view) {

        String url = getString(R.string.meditation_app_url);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    /**
     *
     */
    private void setButtonListeners() {

        Button button;

        button = findViewById(R.id.buttonAboutScore);
        if (button != null) {
            button.setOnClickListener(this::displayAboutScore);
        }

        button = findViewById(R.id.buttonRetakeTest);
        if (button != null) {
            button.setOnClickListener(this::retakeTest);
        }

        button = findViewById(R.id.buttonLearnMore);
        if (button != null) {
            button.setOnClickListener(this::learnMore);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).showAd(this);

        SharedPreferences preferences =
            getApplicationContext().getSharedPreferences("score", MODE_PRIVATE);
        String key = getString(R.string.extra_key_score);
        score = preferences.getFloat(key, 0.0f);

        setContentView(R.layout.activity_score);
        setButtonListeners();

        displayScore();
        displayProgress();
        displayMeditationAppLink();
    }
}