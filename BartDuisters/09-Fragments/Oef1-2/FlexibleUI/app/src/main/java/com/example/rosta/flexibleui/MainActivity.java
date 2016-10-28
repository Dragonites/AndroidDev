package com.example.rosta.flexibleui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NameListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Deze functie wordt aangeroepen in ListFragment en stuurt door naar DetailFragment (Loosely coupled)
    @Override
    public void setNames(int position) {

        // Controleer of 'fragment_container2' (de fragment met 'DetailFragment')
        if (findViewById(R.id.fragment_container2) != null) {
            // Indien het bestaat haal je deze op via de Fragmentmanager, belangrijk hier is dat je het id van de container met het fragment in
            // ophaalt en niet het id van het fragment zelf!
            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
            // Roep de functie in DetailFragment aan
            detailFragment.setFirstNameAndLastNameFromPosition(position);
        } else {
            // Indien het niet bestaat, stuur je door naar DetailActivity, die op zijn beurt fragment_container2 bevat
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }

    }
}
