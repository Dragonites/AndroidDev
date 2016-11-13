package com.example.rosta.elections2016;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        // Dit wordt enkel in deze 'easy'-versie zo opgelost. Indien je met een database werkt, zou je hier de gegevens uit de database halen.
        // Vraag alle resources op
        Resources res = getResources();
        // Haal de verschillende string-arrays uit de resources
        String[] parties = res.getStringArray(R.array.parties);
        String[] names = res.getStringArray(R.array.names);
        String[] ages = res.getStringArray(R.array.ages);
        String[] votes = res.getStringArray(R.array.votes);

        // Haal de gegevens op, op positie 'position'
        String party = parties[position];
        String name = names[position];
        String age = ages[position];
        String vote = votes[position];

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
            case "Democratic Party":
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
    }
}
