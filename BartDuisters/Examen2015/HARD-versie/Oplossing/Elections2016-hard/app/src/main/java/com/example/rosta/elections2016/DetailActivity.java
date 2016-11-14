package com.example.rosta.elections2016;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Rosta on 12-11-2016.
 */

// Indien zelf aangemaakt, 'extends Activity' toevoegen
// TODO: VOEG DE NIEUWE ACTIVITY TOE AAN HET MANIFEST manifests/AndroidManifest.xml
// implements DetailListener pas toevoegen wanneer je het tegenkomt in het stappenplan (DetailListener)
public class DetailActivity extends Activity implements DetailListener {

    // BELANGRIJK: als je onCreate override en er zou als parameter 'PersistableBundle persistentState' bijstaan
    // WIS DIT, alsook 'persistentState' wissen uit super.onCreate
    // Doe je dit niet, dan start je een empty activity in plaats van diegene die je wilt starten via de intent
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        // Pas toevoegen wanneer je het tegenkomt in het stappenplan (DetailListener)
        // Haal de doorgegeven positie op
        Intent intent = getIntent();
        // -1 is de waarde die toegekend wordt indien position niet bestaat
        int position = intent.getIntExtra("position", -1);

        // Onnodige check, aangezien de index van het geklikte item wordt doorgegeven
        if (position != -1) {
            setFieldsAndPicture(position);
        }
        // Tot hier (DetailListener)
    }

    // Deze functie pas toevoegen wanneer je het tegenkomt in het stappenplan (DetailListener)
    @Override
    public void setFieldsAndPicture(int position) {
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
        // Roep de functie in DetailFragment aan
        detailFragment.setAllFieldsAndImageFromPosition(position);
    }

    // (Vote Button)
    public void voteButtonClicked(View view) {
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
        // Roep de functie op om vote te verhogen, deze wordt in de 'easy'-versie niet opgeslagen
        detailFragment.upVote();
    }
}
