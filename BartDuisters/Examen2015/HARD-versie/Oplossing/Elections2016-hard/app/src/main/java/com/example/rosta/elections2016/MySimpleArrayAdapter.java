package com.example.rosta.elections2016;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rosta on 11-11-2016.
 */

// extends ArrayAdapter<String> toevoegen wanneer je dit zelf zou aanmaken i.p.v. copy/paste!
public class MySimpleArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    /** Dit waren String[]-velden in EASY-versie, nu refactor naar List<String>-velden */
    private final List<String> candidates;
    private final List<String> parties;
    /** */

    /* Deze constructor overnemen, de auto-generated constructors zien er anders uit.
    *  Verander de List<String> candidates en List<String> parties naar hetgeen nodig is voor
    *  jouw opdracht.
    */
    /** String[]-parameters waren in EASY-versie nodig, nu refactor naar List<String>-parameters */
    MySimpleArrayAdapter(Context context, List<String> candidates, List<String> parties) {
        /*
        * Het maakt niet uit welke List<String> je meegeeft als derde parameter.
        */
        super(context, -1, candidates);
        this.context = context;
        this.candidates = candidates;
        this.parties = parties;
    }

    /*
     * Deze view wordt per row opgeroepen (in dit geval dus voor elke kandidaat)
     * position wordt met 1 opgehoogd per row die afgehandeld is
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Stel de custom row_layout in
        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        // Haal ImageView en TextView op, deze komen uit row_layout
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView textView = (TextView) rowView.findViewById(R.id.name);

        // Stel de naam in
        /** candidates.get(position) was eerst candidates[position] in EASY-versie omdat daar met String[] werd gewerkt i.p.v. List<String> */
        textView.setText(candidates.get(position));

        // Stel een aangepaste afbeelding in, afhankelijk van de partij waar ze bijzitten
        /** parties.get(position) was eerst parties[position] in EASY-versie omdat daar met String[] werd gewerkt i.p.v. List<String> */
        if (parties.get(position).equals("Republican Party")) {
            imageView.setImageResource(R.drawable.republican_party);
        /** 'Democratic Party' veranderen naar 'Democratic party' wegens inconsistentie in aangeleverde JSON */
        } else if (parties.get(position).equals("Democratic party")) {
            imageView.setImageResource(R.drawable.democratic_party);
        } else {
            imageView.setImageResource(R.drawable.usa_disc);
        }

        return rowView;
    }
}
