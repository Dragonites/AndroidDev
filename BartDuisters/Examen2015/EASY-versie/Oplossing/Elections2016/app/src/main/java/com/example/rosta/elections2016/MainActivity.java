package com.example.rosta.elections2016;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

// DetailListener pas toevoegen wanneer je dit tegenkomt in het stappenplan (DetailListener)
public class MainActivity extends Activity implements DetailListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Deze functie pas toevoegen wanneer je het tegenkomt in het stappenplan (DetailListener)
    @Override
    public void setFieldsAndPicture(int position) {

        /*
        * De comments met sterretjes weghalen en de rest hieronder in comments zetten indien je
        * niet wilt dat landscape mode werkt, en enkel wilt werken met portrait mode
        * Wanneer je het stappenplan volgt, kan je best deze eerst uncommenten en onderstaande code
        * in comments zetten.
        // Onderstaande code is nodig om de nieuwe Activity (met de nieuwe Fragment) te openen
        // De aangeklikte positie wordt doorgegeven naar de volgende Activity
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
        */

        // Controleer of 'fragment_container2' (de fragment met 'DetailFragment')
        if (findViewById(R.id.fragment_container2) != null) {
            // Indien het bestaat haal je deze op via de Fragmentmanager, belangrijk hier is dat je het id van de container met het fragment in
            // ophaalt en niet het id van het fragment zelf!
            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
            // Roep de functie in DetailFragment aan
            detailFragment.setAllFieldsAndImageFromPosition(position);
        } else {
            // Indien het niet bestaat, stuur je door naar DetailActivity, die op zijn beurt fragment_container2 bevat
            // Dit is dezelfde code als boven in comments staat
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

    // (Vote Button)
    public void voteButtonClicked(View view) {
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
        // Roep de functie op om vote te verhogen, deze wordt in de 'easy'-versie niet opgeslagen
        detailFragment.upVote();
    }

    // (Option Menu)
    // Deze code en onOptionsItemSelected in elke Activity toevoegen waar je de settingsknop wilt hebben.
    // Ik hou het simpel en voeg hem enkel aan MainActivity toe.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    // (Option Menu)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("settings")) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
