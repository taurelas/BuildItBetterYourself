package com.leadinsource.andjoker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JokeDisplay extends AppCompatActivity {

    public static final String JOKE_KEY = "com.leadinsource.andjoker.joke_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        Intent intent = getIntent();

        setTitle(getString(R.string.a_random_joke));

        if(intent==null) {
            finish();
        } else {
            TextView textView = findViewById(R.id.textView);
            textView.setText(intent.getStringExtra(JOKE_KEY));
        }
    }
}
