package com.example.rosta.volume;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Rosta on 7-10-2016.
 */
public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();
        if (intent.hasExtra("showVolumeSettings")){
            CharSequence text = intent.getExtras().getCharSequence("showVolumeSettings");
            if (text != null){
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }






    }

    /** Called when the user clicks the Send button */
    public void openVolumeActivity(View view) {
        Intent intent = new Intent(MainActivity.this, VolumeActivity.class);
        intent.putExtra("defaultValue", 50);
        startActivity(intent);
    }
}
