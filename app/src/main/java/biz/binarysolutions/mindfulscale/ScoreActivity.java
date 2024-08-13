package biz.binarysolutions.mindfulscale;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        view.setText(String.format("%.2f", score));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).showAd(this);

        SharedPreferences preferences =
            getApplicationContext().getSharedPreferences("score", MODE_PRIVATE);
        String key = getString(R.string.extra_key_score);
        score = preferences.getFloat(key, 0.0f);

        setContentView(R.layout.activity_score);
        displayScore();
        displayProgress();
        displayMeditationAppLink();
    }

    /**
     *
     * @param view
     */
    public void displayAboutScore(View view) {

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
    public void retakeTest(View view) {
        startActivity(new Intent(this, PretestActivity.class));
    }

    /**
     *
     * @param view
     */
    public void findOutMore(View view) {

        String url = "https://github.com/vbresan/MindfulnessMeditation/";

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}