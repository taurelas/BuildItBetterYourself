package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.leadinsource.andjoker.JokeDisplay;
import com.leadinsource.joker.Joker;


public class MainActivity extends AppCompatActivity implements ActivityCallback {

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.progressBar);

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
        Intent intent = new Intent(this, JokeDisplay.class);
        intent.putExtra(JokeDisplay.JOKE_KEY, Joker.getJoke());
        startActivity(intent);
    }


    public void tellJokeViaApi(View view) {
        spinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }

    public void onResult(String joke) {
        spinner.setVisibility(View.GONE);
        Intent intent = new Intent(this, JokeDisplay.class);
        intent.putExtra(JokeDisplay.JOKE_KEY, joke);
        startActivity(intent);
    }
}
