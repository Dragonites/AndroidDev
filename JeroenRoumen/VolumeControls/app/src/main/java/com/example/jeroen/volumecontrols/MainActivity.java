package com.example.jeroen.volumecontrols;

import android.content.Context;
import android.content.DialogInterface;
import android.os.RecoverySystem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Give OK Button event handler
        Button btn = (Button)findViewById(R.id.okKnop);
        btn.setOnClickListener(this);
        //Give Reset Button event handler
        Button btn1 = (Button)findViewById(R.id.resetKnop);
        btn1.setOnClickListener(this);

        //Seekbar onProgressChanged
        SeekBar skb = (SeekBar)findViewById(R.id.beltoonSlider);
        skb.setOnSeekBarChangeListener(mySeekBarListener);
    }

    private SeekBar.OnSeekBarChangeListener mySeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            CheckBox checkbox = (CheckBox) findViewById(R.id.checkBoxMelding);
            if (checkbox.isChecked()) {
                int volume = ((SeekBar) findViewById(R.id.beltoonSlider)).getProgress();
                SeekBar skb = (SeekBar)findViewById(R.id.meldingSlider);
                skb.setProgress(volume);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //not implemented yet
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //not implemented yet
        }
    };

    @Override
    public void onClick(View v) {
        int naamKnop = v.getId();
        if (v.getId() == R.id.okKnop) {
            //get seekbar values
            SeekBar seekBar = (SeekBar)findViewById(R.id.beltoonSlider);
            int beltoonVal = seekBar.getProgress();
            seekBar = (SeekBar)findViewById(R.id.mediaSlider);
            int mediaVal = seekBar.getProgress();
            seekBar = (SeekBar)findViewById(R.id.alarmSlider);
            int alarmVal = seekBar.getProgress();
            seekBar = (SeekBar)findViewById(R.id.mediaSlider);
            int meldingVal = seekBar.getProgress();

            //show values in a toast
            Context context = getApplicationContext();
            CharSequence text = "Beltoon: " + beltoonVal + "\nMedia: " + mediaVal
                    + "\nAlarm: " + alarmVal + "\nMelding: " + meldingVal;
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            //set values of seekbars to 0
            SeekBar seekBar = (SeekBar)findViewById(R.id.beltoonSlider);
            seekBar.setProgress(1);
            seekBar = (SeekBar)findViewById(R.id.mediaSlider);
            seekBar.setProgress(1);
            seekBar = (SeekBar)findViewById(R.id.alarmSlider);
            seekBar.setProgress(1);
            seekBar = (SeekBar)findViewById(R.id.meldingSlider);
            seekBar.setProgress(1);
        }

    }
}
