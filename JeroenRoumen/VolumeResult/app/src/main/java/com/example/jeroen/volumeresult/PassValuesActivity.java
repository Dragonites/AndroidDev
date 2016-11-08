package com.example.jeroen.volumeresult;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class PassValuesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_values);
        //ophalen gegevens sliders
        int[] sliderWaarden = getIntent().getIntArrayExtra("sliderWaarden");
        Context context = getApplicationContext();
        String tekst = "Alarmslider: " + sliderWaarden[0] + " Mediaslider: " + sliderWaarden[1] +
                "\nMeldingslider: " + sliderWaarden[2] + " Beltoonslider: " + sliderWaarden[3];
        Toast toast = Toast.makeText(context, tekst, Toast.LENGTH_SHORT);
        toast.show();
    }
}
