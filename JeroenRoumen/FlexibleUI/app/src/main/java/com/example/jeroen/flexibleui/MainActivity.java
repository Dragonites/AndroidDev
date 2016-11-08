package com.example.jeroen.flexibleui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements MyListener {
    FragmentManager manager = getFragmentManager();

    String firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendFirstLastName(int position) {
        //get fragment B (detail)
        ListView listView = (ListView) findViewById(R.id.lvNamen);
        String[] names = ((String) listView.getItemAtPosition(position)).split(" ");
        this.firstName = names[0];
        this.lastName = names[1];
        sendDataToFragmentB();
    }

    public void sendDataToFragmentB() {
        FragmentB fragmentB = (FragmentB) manager.findFragmentByTag("fragB");
        fragmentB.setFirstLastNameInFragmentB(this.firstName, this.lastName);
    }

}
