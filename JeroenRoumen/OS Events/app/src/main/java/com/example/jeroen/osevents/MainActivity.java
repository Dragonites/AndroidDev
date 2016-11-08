package com.example.jeroen.osevents;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        listview.setOnItemClickListener(mMessageClickedHandler);
    }

    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Context context = getApplicationContext();
            String tekst = "Clicked item on position " + position;
            Toast toast = Toast.makeText(context, tekst, Toast.LENGTH_SHORT);
            toast.show();
        }
    };
}
