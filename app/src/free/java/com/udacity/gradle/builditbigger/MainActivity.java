package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.leadinsource.andjoker.JokeDisplay;
import com.leadinsource.joker.Joker;


public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private InterstitialAd interstitialAd;
    private boolean useApi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/1033173712");
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                if(useApi) {
                    new EndpointsAsyncTask().execute(MainActivity.this);
                } else {
                    Intent intent = new Intent(MainActivity.this, JokeDisplay.class);
                    intent.putExtra(JokeDisplay.JOKE_KEY, Joker.getJoke());
                    startActivity(intent);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        useApi = false;
        if(interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Toast.makeText(this, "Try again later or use paid version :) Only £199", Toast.LENGTH_SHORT).show();
        }

    }


    public void tellJokeViaApi(View view) {
        useApi = true;
        if(interstitialAd.isLoaded()) {
            interstitialAd.show();
        }else {
            Toast.makeText(this, "Try again later or use paid version :) Only £199", Toast.LENGTH_SHORT).show();
        }


    }

    public void onResult(String joke) {
        Intent intent = new Intent(this, JokeDisplay.class);
        intent.putExtra(JokeDisplay.JOKE_KEY, joke);
        startActivity(intent);
    }
}
