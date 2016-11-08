package com.example.jeroen.flexibleuin;


import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setNames(String firstname, String lastname) {

        if (findViewById(R.id.fragment_container2) != null) {
            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
            detailFragment.setFirstAndLastName(firstname, lastname);
        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("firstname", firstname);
            intent.putExtra("lastname", lastname);
            startActivity(intent);
        }


    }

}
