package com.example.jeroen.os3;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //string array vullen uit xml
        Resources res = getResources();
        String[] osList = res.getStringArray(R.array.osLijst);
        //ArrayAdapter aanmaken en vullen
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, osList);
        //listview vanuit xml aanspreken en koppelen adapter
        ListView listview = (ListView)findViewById(R.id.osLijst);
        listview.setAdapter(adapter);
    }
}
