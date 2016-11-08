package com.example.jeroen.volumedefault;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

public class DefaultVolumeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_volume);

        //waarde ophalen van de andere activity
        Integer waarde = getIntent().getIntExtra("DefaultWaarde", 0);
        //toast aanmaken om waarde te laten zien
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, waarde.toString(), Toast.LENGTH_SHORT);
        toast.show();
        //waarde van de sliders aanpassen naar de default waarde
        SeekBar seekbar = (SeekBar)findViewById(R.id.beltoonSlider);
        seekbar.setProgress(waarde);
        seekbar = (SeekBar)findViewById(R.id.alarmSlider);
        seekbar.setProgress(waarde);
        seekbar = (SeekBar)findViewById(R.id.mediaSlider);
        seekbar.setProgress(waarde);
        seekbar = (SeekBar)findViewById(R.id.meldingSlider);
        seekbar.setProgress(waarde);
    }
}
