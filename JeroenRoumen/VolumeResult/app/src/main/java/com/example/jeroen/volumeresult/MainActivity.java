package com.example.jeroen.volumeresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showValues(View view) {
        //sliderwaarden in een array stoppen
        int[] sliderWaarden = new int[4];
        sliderWaarden[0] = ((SeekBar)findViewById(R.id.alarmSlider)).getProgress();
        sliderWaarden[1] = ((SeekBar)findViewById(R.id.mediaSlider)).getProgress();
        sliderWaarden[2] = ((SeekBar)findViewById(R.id.meldingSlider)).getProgress();
        sliderWaarden[3] = ((SeekBar)findViewById(R.id.beltoonSlider)).getProgress();
        //intent aanmaken
        Intent intent = new Intent(this, PassValuesActivity.class);
        intent.putExtra("sliderWaarden", sliderWaarden);
        startActivity(intent);
    }
}
