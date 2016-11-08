package com.example.jeroen.flexibleuin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Jeroen on 07/11/2016.
 */

public class DetailActivity extends AppCompatActivity implements MyListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //waarden ophalen als het fragment nog niet bestond
        Intent intent = getIntent();
        String firstname = intent.getStringExtra("firstname");
        String lastname = intent.getStringExtra("lastname");

        if (firstname.equals(null) || lastname.equals(null)) {
            Toast.makeText(this, "No names found", Toast.LENGTH_SHORT).show();
        } else {
            setNames(firstname, lastname);
        }
    }

    @Override
    public void setNames(String firstname, String lastname) {
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
        detailFragment.setFirstAndLastName(firstname, lastname);
    }
}
