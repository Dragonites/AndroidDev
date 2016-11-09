package com.example.rosta.condiments;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by rosta on 11/2/16.
 */

public class CountryCodeContract {


    // Voorkomen dat iemand per ongeluk een instantie maakt van deze klasse door een lege constructor te voorzien
    private CountryCodeContract() {}

    /**
     * De Content Authority is een naam voor de volledige content provider, je kan het vergelijken
     * met de relatie tussen een domeinnaam en de website die terug te vinden is op deze domeinnaam.
     * Een veelgebruikte string voor de content authority is de packagenaam van de app, aangezien
     * deze gegarandeerd uniek is op het apparaat.
     *
     * com.bartduisters zou dus verschillend zijn, het laatste deel mag hetzelfde zijn.
     */
    public static final String CONTENT_AUTHORITY = "com.bartduisters.countrycodesdatabase";

    /**
     * De content authority wordt gebruikt als basis van alle URI's die andere applicaties
     * zullen gebruiken om deze Content Provider te gebruiken.
     *
     * Elke URI van een Content Provider wordt voorafgegaan door "content://".
     */
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    /**
     * Elke String vormt een mogelijk pad dat wordt toegevoegd aan de 'base URI', dit voor elke
     * tabel die terug te vinden is in de database.
     */
    public static final String PATH_CONDIMENT = "countrycode";


    /**
     * Maak één class voor elke tabel die alle informatie omtrent deze tabel en alle URI's
     * gerelateerd aan deze tabel afhandelt.
     *
     * Implement BaseColumns om _ID toe te voegen aan de class, deze wordt gebruikt
     * om id auto-increment toe te kunnen passen.
     */
    public static final class CountryCodeEntry implements BaseColumns {
        // Content URI om de 'base' van de tabel voor te stellen
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONDIMENT).build();

        // Dit zijn speciale voorvoegsels die het type specifieëren van de URI, dit bepaalt
        // of de URI een lijst of een specifiek item teruggeeft
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_CONDIMENT;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_CONDIMENT;

        // Definieer hoe de tabel eruit zal zien
        public static final String TABLE_NAME = "countrycodeTable";
        public static final String COLUMN_NAME = "countryName";
        public static final String COLUMN_CODE = "countryCode";
        public static final String COLUMN_NUMBER = "countryNumber";

        // Definieer een functie om een URI te maken om een specifieke item te vinden via de ID
        public static Uri buildCondimentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
