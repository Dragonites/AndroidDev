package com.example.rosta.elections2016;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.rosta.elections2016.Candidate.Candidate;
import com.example.rosta.elections2016.Candidate.ElectionUtils;
import com.example.rosta.elections2016.database.ElectionContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rosta on 11-11-2016.
 */
public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        // Ophalen van de ListView zodat we custom adapter kunnen zetten (onderaan)
        ListView listView = (ListView) view.findViewById(R.id.list_fragment);

        /**
         * De code in comments is de code die nodig was in de 'easy'-versie,
         * ik laat deze staan zodat mensen die de easy en hard gemaakt hebben het verschil
         * in code zien.
         */
//        String[] candidates = getResources().getStringArray(R.array.names);
//        String[] parties = getResources().getStringArray(R.array.parties);
        List<String> names = new ArrayList<>();
        List<String> parties = new ArrayList<>();

        /** Deze code is nieuw, hiermee haal je de lijsten op via de Content Provider */
        // ContentResolver om de ContentProvider te queries door te sturen
        ContentResolver resolver = getActivity().getContentResolver();
        // De kolommen die we willen opvragen
        String[] projection = new String[]{ElectionContract.Candidates.COL_NAME, ElectionContract.Candidates.COL_PARTY, ElectionContract.Candidates.COL_AGE, ElectionContract.Candidates.COL_VOTES};

        // Bevat het resultaat (alle rijen)
        Cursor cursor = resolver.query(ElectionContract.Candidates.CONTENT_URI, projection,
                null, null, null);

        // Gestolen van Toon
        // Als de database leeg is, seed je de database en dan vraag je de data op
        if (cursor.getCount() == 0) {
            seedCandidateTable(); // Staat onderaan
            cursor = resolver.query(ElectionContract.Candidates.CONTENT_URI, projection,
                    null, null, null);
        }

        // Ga naar de eerste rij
        cursor.moveToFirst();
        // Zolang de cursor niet achter de laatste rij staat..
        while (!cursor.isAfterLast()) {
            // 0 en 1 slaan terug op de positie van de String[] in de projection. In dit geval
            // heb ik enkel COL_NAME en COL_PARTY opgevraagd, meer wordt niet weergegeven in de lijst
            String name = cursor.getString(0);
            String party = cursor.getString(1);

            // Toevoegen aan de lijsten
            names.add(name);
            parties.add(party);

            // .. blijf je doorgaan naar de volgende rij
            cursor.moveToNext();
        }
        /** Tot hier nieuwe code /ContentProvider */

        /** De parameter 'names' was 'candidates' in EASY-versie, omdat de lijst een andere naam had daar */
        MySimpleArrayAdapter cumstomAdapter = new MySimpleArrayAdapter(getActivity(), names, parties);
        listView.setAdapter(cumstomAdapter);

        // Deze code pas toevoegen wanneer je het tegenkomt in het stappenplan (DetailListener)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Wanneer er een rij aangeklikt wordt, sturen we de positie door naar de Listener
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailListener detailListener = (DetailListener) getActivity();
                detailListener.setFieldsAndPicture(position);
            }
        });
        // Tot hier (DetailListener)

        return view;
    }

    // Database voorzien van data
    private void seedCandidateTable() {
        List<Candidate> candidates = ElectionUtils.getCandidates(getActivity().getApplicationContext());
        ContentValues values = new ContentValues();
        ContentResolver resolver = getActivity().getContentResolver();

        for (Candidate c : candidates) {
            values.put(ElectionContract.Candidates.COL_NAME, c.getName());
            values.put(ElectionContract.Candidates.COL_PARTY, c.getParty());
            values.put(ElectionContract.Candidates.COL_AGE, c.getAge());
            values.put(ElectionContract.Candidates.COL_VOTES, c.getVotes());
            resolver.insert(ElectionContract.Candidates.CONTENT_URI, values);
        }

    }
}
