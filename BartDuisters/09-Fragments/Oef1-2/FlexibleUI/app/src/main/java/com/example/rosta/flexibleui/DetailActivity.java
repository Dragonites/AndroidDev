package com.example.rosta.flexibleui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by rosta on 10/28/16.
 */

// Vergeet niet om nieuwe Activities in het manifest te declareren!
public class DetailActivity extends AppCompatActivity implements NameListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Haal de doorgegeven positie op
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);

        // Onnodige check, aangezien de index van het geklikte item wordt doorgegeven
        if (position == -1) {
            Toast.makeText(this, "Index not found", Toast.LENGTH_SHORT).show();
        } else {
            setNames(position);
        }
    }

    @Override
    public void setNames(int position) {
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
        // Roep de functie in DetailFragment aan
        detailFragment.setFirstNameAndLastNameFromPosition(position);
    }
}
