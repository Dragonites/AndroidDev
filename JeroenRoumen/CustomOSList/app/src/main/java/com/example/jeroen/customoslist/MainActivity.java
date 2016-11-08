package com.example.jeroen.customoslist;

import android.app.ListActivity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] osLijst = getResources().getStringArray(R.array.osList);
        OperatingSystemsAdapter customAdapter = new OperatingSystemsAdapter(getApplicationContext(), osLijst);
        setListAdapter(customAdapter);
    }
}
