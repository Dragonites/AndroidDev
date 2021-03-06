package com.example.jeroen.volumedefault;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendVolumeMessage(View view) {
        //nieuwe intent aanmaken en defaultwaarde meegeven
        Intent intent = new Intent(this, DefaultVolumeActivity.class);
        intent.putExtra("DefaultWaarde", 50);
        startActivity(intent);
    }
}
