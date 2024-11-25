package biz.binarysolutions.mindfulscale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

/**
 *
 */
public class FlavorSpecific {

    private final Activity activity;

    /**
     *
     * @param id
     * @return
     */
    @SuppressWarnings("SameParameterValue")
    private <T extends View> T findViewById(int id) {
        return activity.findViewById(id);
    }

    /**
     *
     * @param resId
     * @return
     */
    @SuppressWarnings("SameParameterValue")
    private String getString(int resId) {
        return activity.getString(resId);
    }

    /**
     *
     * @param intent
     */
    private void startActivity(Intent intent) {
        activity.startActivity(intent);
    }

    /**
     *
     * @param view
     */
    private void donate(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(R.string.donate_dialog_title);
        builder.setMessage(R.string.donate_dialog_content);

        builder.setPositiveButton(R.string.cont, (dialog, which) -> {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getString(R.string.donation_url)));
            startActivity(intent);

            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     *
     * @param activity
     */
    public FlavorSpecific(Activity activity) {
        this.activity = activity;
    }

    /**
     *
     */
    public void setButtonListeners() {

        Button button = findViewById(R.id.buttonDonate);
        if (button != null) {
            button.setOnClickListener(this::donate);
        }
    }
}
