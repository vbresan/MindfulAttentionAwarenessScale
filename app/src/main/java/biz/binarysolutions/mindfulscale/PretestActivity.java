package biz.binarysolutions.mindfulscale;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 *
 */
public class PretestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretest);

        TextView view = findViewById(R.id.textViewPretestDescription);
        if (view != null) {
            view.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    /**
     *
     * @param view
     */
    public void startTest(View view) {

        Intent intent = new Intent(this, TestActivity.class);
        String key    = getString(R.string.extra_key_test_index);
        intent.putExtra(key, 0);

        startActivity(intent);
    }
}