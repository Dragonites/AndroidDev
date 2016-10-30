package com.example.rosta.volume;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class VolumeActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    private int defaultValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        // Capture button from layout
        Button buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(buttonOkListener);

        // Capture button from layout
        Button buttonReset = (Button) findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(buttonResetListener);

        // Capture seekbar from layout
        SeekBar seekBarBeltoon = (SeekBar) findViewById(R.id.seekBarBeltoon);
        seekBarBeltoon.setOnSeekBarChangeListener(seekBarBeltoonListener);

        // Grab intent
        Intent intent = getIntent();
        defaultValue = intent.getIntExtra("defaultValue", 0);

        // Set defaut values and toast
        setDefaultValue();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBarBeltoon);
        seekBar.setProgress(settings.getInt("BeltoonValue", 60));
        seekBar = (SeekBar)findViewById(R.id.seekBarMedia);
        seekBar.setProgress(settings.getInt("MediaValue", 40));
        seekBar = (SeekBar)findViewById(R.id.seekBarAlarm);
        seekBar.setProgress(settings.getInt("AlarmValue", 20));
        seekBar = (SeekBar) findViewById(R.id.seekBarMelding);
        seekBar.setProgress(settings.getInt("MeldingValue", 65));

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkboxKak);
        checkBox.setChecked(settings.getBoolean("CheckBox", false));
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBarBeltoon);
        int seekBarBeltoonValue = seekBar.getProgress();
        seekBar = (SeekBar)findViewById(R.id.seekBarMedia);
        int seekBarMediaValue = seekBar.getProgress();
        seekBar = (SeekBar)findViewById(R.id.seekBarAlarm);
        int seekBarAlarmValue = seekBar.getProgress();
        seekBar = (SeekBar) findViewById(R.id.seekBarMelding);
        int seekBarMeldingValue = seekBar.getProgress();

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkboxKak);
        boolean isChecked = checkBox.isChecked();

        editor.putInt("BeltoonValue", seekBarBeltoonValue);
        editor.putInt("MediaValue", seekBarMediaValue);
        editor.putInt("AlarmValue", seekBarAlarmValue);
        editor.putInt("MeldingValue", seekBarMeldingValue);
        editor.putBoolean("CheckBox", isChecked);

        editor.apply();





    }

    private OnClickListener buttonOkListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SeekBar seekBar = (SeekBar)findViewById(R.id.seekBarBeltoon);
            int seekBarBeltoonValue = seekBar.getProgress();
            seekBar = (SeekBar)findViewById(R.id.seekBarMedia);
            int seekBarMediaValue = seekBar.getProgress();
            seekBar = (SeekBar)findViewById(R.id.seekBarAlarm);
            int seekBarAlarmValue = seekBar.getProgress();
            seekBar = (SeekBar) findViewById(R.id.seekBarMelding);
            int seekBarMeldingValue = seekBar.getProgress();

            // Settings for Toast
            CharSequence text =
                    "Beltoon: " + seekBarBeltoonValue + "\n"
                    + "Media: " + seekBarMediaValue + "\n"
                    + "Alarm: " + seekBarAlarmValue + "\n"
                    + "Melding: " + seekBarMeldingValue;

            Intent intent = new Intent(VolumeActivity.this, MainActivity.class);
            intent.putExtra("showVolumeSettings", text);
            startActivity(intent);
        }
    };

    private OnClickListener buttonResetListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // Sound settings
            int seekBarBeltoonValue = 60;
            int seekBarMediaValue = 40;
            int seekBarAlarmValue = 20;
            int seekBarMeldingValue = 65;

            SeekBar seekBar = (SeekBar)findViewById(R.id.seekBarBeltoon);
            seekBar.setProgress(seekBarBeltoonValue);
            seekBar = (SeekBar)findViewById(R.id.seekBarMedia);
            seekBar.setProgress(seekBarMediaValue);
            seekBar = (SeekBar)findViewById(R.id.seekBarAlarm);
            seekBar.setProgress(seekBarAlarmValue);
            seekBar = (SeekBar) findViewById(R.id.seekBarMelding);
            seekBar.setProgress(seekBarMeldingValue);
        }
    };

    private OnSeekBarChangeListener seekBarBeltoonListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            CheckBox checkbox = (CheckBox)(findViewById(R.id.checkboxKak));
            if (checkbox.isChecked()) {
            SeekBar seekBarMelding = (SeekBar)findViewById(R.id.seekBarMelding);
            seekBarMelding.setProgress(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void setDefaultValue() {
        // Set bar values
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBarBeltoon);
        seekBar.setProgress(defaultValue);
        seekBar = (SeekBar)findViewById(R.id.seekBarMedia);
        seekBar.setProgress(defaultValue);
        seekBar = (SeekBar)findViewById(R.id.seekBarAlarm);
        seekBar.setProgress(defaultValue);
        seekBar = (SeekBar) findViewById(R.id.seekBarMelding);
        seekBar.setProgress(defaultValue);

        // Settings for Toast
        Context context = getApplicationContext();
        CharSequence text = "DEFAULT: " + defaultValue;
        int duration = Toast.LENGTH_SHORT;

        // Create and show Toast
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
