package com.example.jeroen.operatingsystems;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    String[] osList = {"Android", "Ubuntu", "Linux Mint", "Manjaro", "Arch Linux"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ArrayAdapter aanmaken en vullen
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, osList);
        //listview vanuit xml aanspreken en koppelen adapter
        ListView listview = (ListView)findViewById(R.id.osLijst);
        listview.setAdapter(adapter);
    }
}
