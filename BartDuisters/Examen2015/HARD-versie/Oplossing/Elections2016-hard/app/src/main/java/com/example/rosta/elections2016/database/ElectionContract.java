package com.example.rosta.elections2016.database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by stilkin on 5/11/15.
 */
public class ElectionContract {
    public static final String DB_NAME = "election_db";
    public static final int DB_VER = 1;

    public static final class Candidates implements BaseColumns {
        public static final String TABLE_NAME = "candidates";
        public static final String COL_NAME = "name";
        public static final String COL_PARTY = "party";
        public static final String COL_AGE = "age";
        public static final String COL_VOTES = "votes";

        // EXTRA CODE OM TE GEBRUIKEN IN DBHelper EN Provider!

        // Content URI om de 'base' van de tabel voor te stellen
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONDIMENT).build();

        // Dit zijn speciale voorvoegsels die het type specifieëren van de URI, dit bepaalt
        // of de URI een lijst of een specifiek item teruggeeft
        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_CONDIMENT;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_CONDIMENT;

        // Definieer een functie om een URI te maken om een specifieke film te vinden via de ID
        public static Uri buildCondimentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    // EXTRA CODE OM TE GEBRUIKEN IN DBHelper EN Provider!
    /**
     * De Content Authority is een naam voor de volledige content provider, je kan het vergelijken
     * met de relatie tussen een domeinnaam en de website die terug te vinden is op deze domeinnaam.
     * Een veelgebruikte string voor de content authority is de packagenaam van de app, aangezien
     * deze gegarandeerd uniek is op het apparaat.
     *
     * com.bartduisters zou dus verschillend zijn, het laatste deel mag hetzelfde zijn.
     */
    public static final String CONTENT_AUTHORITY = "com.bartduisters.candidatedatabase";

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
    public static final String PATH_CONDIMENT = "condiment";
}
