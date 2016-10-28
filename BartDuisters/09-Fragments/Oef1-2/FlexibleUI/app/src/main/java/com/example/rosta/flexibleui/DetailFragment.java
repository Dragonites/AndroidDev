package com.example.rosta.flexibleui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rosta on 10/27/16.
 */
public class DetailFragment extends Fragment {
    private EditText etFirstName, etLastName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.detail_fragment, container, false);

        // Haal de tekstvelden op uit de huidige view (fragment)
        etFirstName = (EditText) fragmentView.findViewById(R.id.editTextFirstname);
        etLastName = (EditText) fragmentView.findViewById(R.id.editTextLastname);

        return fragmentView;
    }

    public void setFirstNameAndLastNameFromPosition(int position) {
        // Vraag alle resources op
        Resources res = getResources();
        // Haal de 'naam array' op uit de resources
        String[] names = res.getStringArray(R.array.names);

        // Filter de naam op 'position'
        String name = names[position];

        // Split de naam in voornaam en achternaam op basis van spatie + zet
        String[] splittedName = name.split(" ");

        // Zet de voornaam en achternaam
        etFirstName.setText(splittedName[0]);
        etLastName.setText(splittedName[1]);

    }
}
