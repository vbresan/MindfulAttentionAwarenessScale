package biz.binarysolutions.mindfulscale;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

/**
 *
 */
public class App extends Application {

    private InterstitialAd interstitialAd;

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, initializationStatus -> {

            InterstitialAd.load(
                this,
                getString(R.string.admob_ad_id),
                new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd ad) {
                        interstitialAd = ad;
                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                interstitialAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                interstitialAd = null;
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError error) {
                        interstitialAd = null;
                    }
                });
        });
    }

    /**
     *
     * @param activity
     */
    public void showAd(Activity activity) {

        if (interstitialAd != null) {
            interstitialAd.show(activity);
        }
    }
}
