package com.example.rosta.elections2016;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rosta.elections2016.database.ElectionContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rosta on 12-11-2016.
 */

// Indien zelf aangemaakt, zorg dat je 'extends' van 'Fragment'
public class DetailFragment extends Fragment {

    private TextView partyField, nameField, ageField, votesField;
    private ImageView candidatePictureField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Haal Fragment-layout op
        View fragmentView = inflater.inflate(R.layout.detail_fragment, container, false);

        // Haal alle verschillende views op
        partyField = (TextView) fragmentView.findViewById(R.id.candidate_party);
        nameField = (TextView) fragmentView.findViewById(R.id.candidate_name);
        ageField = (TextView) fragmentView.findViewById(R.id.candidate_age);
        votesField = (TextView) fragmentView.findViewById(R.id.votes);
        candidatePictureField = (ImageView) fragmentView.findViewById(R.id.candidate_picture);

        return fragmentView;
    }

    // Deze functie zal opgeroepen worden via DetailActivity en MainActivity, op een instantie van DetailFragment
    // Todo: Veel logica in één functie --> meerdere functies van maken
    public void setAllFieldsAndImageFromPosition(int position) {
        /**
         * De code in comments is de code die nodig was in de 'easy'-versie,
         * ik laat deze staan zodat mensen die de easy en hard gemaakt hebben het verschil
         * in code zien.
         */
        // Vraag alle resources op
//        Resources res = getResources();
        // Haal de verschillende string-arrays uit de resources
//        String[] parties = res.getStringArray(R.array.parties);
//        String[] names = res.getStringArray(R.array.names);
//        String[] ages = res.getStringArray(R.array.ages);
//        String[] votes = res.getStringArray(R.array.votes);
        List<String> names = new ArrayList<>();
        List<String> parties = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        List<Integer> votes = new ArrayList<>();

        /** Deze code is nieuw, hiermee haal je de lijsten op via de Content Provider */
        // ContentResolver om de ContentProvider te queries door te sturen
        ContentResolver resolver = getActivity().getContentResolver();
        // De kolommen die we willen opvragen
        String[] projection = new String[]{ElectionContract.Candidates.COL_NAME, ElectionContract.Candidates.COL_PARTY, ElectionContract.Candidates.COL_AGE, ElectionContract.Candidates.COL_VOTES};

        // Bevat het resultaat (alle rijen)
        Cursor cursor = resolver.query(ElectionContract.Candidates.CONTENT_URI, projection,
                null, null, null);

        // Ga naar de eerste rij
        cursor.moveToFirst();
        // Zolang de cursor niet achter de laatste rij staat..
        while (!cursor.isAfterLast()) {
            // 0, 1, 2 en 3 slaan terug op de positie van de String[] in de projection. In dit geval
            // heb ik eerst COL_NAME, dan COL_PARTY, dan COL_AGE en uiteindelijk COL_VOTES opgevraagd
            String name = cursor.getString(0);
            String party = cursor.getString(1);
            int age = cursor.getInt(2);
            int vote = cursor.getInt(3);

            // Toevoegen aan de lijsten
            names.add(name);
            parties.add(party);
            ages.add(age);
            votes.add(vote);

            // .. blijf je doorgaan naar de volgende rij
            cursor.moveToNext();
        }
        /** Tot hier nieuwe code /ContentProvider */

        // Haal de gegevens op, op positie 'position'
//        String party = parties[position];
//        String name = names[position];
//        String age = ages[position];
//        String vote = votes[position];
        String party = parties.get(position);
        String name = names.get(position);
        String age = ages.get(position).toString(); // toString() omdat het als INTEGER wordt bijgehouden in database, maar TextView in layout verwacht String
        String vote = votes.get(position).toString(); // toString() omdat het als INTEGER wordt bijgehouden in database, maar TextView in layout verwacht String

        // Logica om de voornaam en achternaam om te vormen naar lower case en een _ tussen beide te plaatsen
        // Dit komt overeen met de naam van de afbeelding in res/drawable/
        String[] splittedName = name.split(" ");
        String imageName = splittedName[0].toLowerCase() + "_" + splittedName[1].toLowerCase();

        // Voeg alles toe aan de velden
        partyField.setText(party);
        nameField.setText(name);
        ageField.setText(age);
        votesField.setText(vote);

        // Controleer of de image resource bestaat. Als het bestaat krijg je het resource ID terug
        /** Dit stond bij EASY-versie bovenaan, refactor naar hier omdat enkel hier resources aangesproken worden */
        // Vraag alle resources op
        Resources res = getResources();
        /** /resources */
        int checkIfImageExcist = res.getIdentifier(imageName, "drawable", getActivity().getPackageName());
        if (checkIfImageExcist != 0) {
            // image bestaat
            candidatePictureField.setImageResource(checkIfImageExcist);
        } else {
            // image bestaat niet
            candidatePictureField.setImageResource(R.drawable.usa_disc);
        }

        // Controleer bij welke party ze horen en pas achtergrond van ImageView aan op basis van party
        switch (party) {
            case "Republican Party":
                candidatePictureField.setBackgroundColor(Color.RED);
                break;
                /** Democratic party i.p.v. Democratic Party door inconsistentie in aangeleverde JSON */
            case "Democratic party":
                candidatePictureField.setBackgroundColor(Color.BLUE);
                break;
            default:
                candidatePictureField.setBackgroundColor(Color.GREEN);
                break;
        }

    }

    // (Vote Button)
    // Hier komt normaal de SQLite-logica waarin je de waarde uit SQLite-database haalt, ophoogt en terug wegschrijft
    public void upVote() {
        // Vraag de huidige vote value op
        String currentVote = votesField.getText().toString();
        // Parse naar int
        int currentVoteValue = Integer.parseInt(currentVote);
        // Verhoog vote value
        currentVoteValue++;
        // Stel nieuwe waarde in als huidige waarde
        votesField.setText(currentVoteValue + "");

        /** Nieuwe value opslaan naar Database */
        ContentValues contentValues = new ContentValues();
        ContentResolver contentResolver = getActivity().getContentResolver();

        contentValues.put(ElectionContract.Candidates.COL_NAME, nameField.getText().toString());
        contentValues.put(ElectionContract.Candidates.COL_PARTY, partyField.getText().toString());
        contentValues.put(ElectionContract.Candidates.COL_AGE, ageField.getText().toString());
        contentValues.put(ElectionContract.Candidates.COL_VOTES, votesField.getText().toString());
        // contentResolver.update(URI, ContentValues, WHERE-clause, null);
        contentResolver.update(ElectionContract.Candidates.CONTENT_URI, contentValues, "name='" + nameField.getText().toString() + "'", null);

        // Melding geven dat het gelukt is
        Toast.makeText(this.getActivity(), "saved", Toast.LENGTH_SHORT).show();
    }
}
