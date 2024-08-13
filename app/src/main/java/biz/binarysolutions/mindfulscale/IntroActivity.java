package biz.binarysolutions.mindfulscale;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        TextView view = findViewById(R.id.textViewDescription);
        if (view != null) {
            view.setMovementMethod(new ScrollingMovementMethod());
        }
    }

    /**
     *
     * @param view
     */
    public void onButtonStartPretestClick(View view) {
        startActivity(new Intent(this, PretestActivity.class));
    }
}