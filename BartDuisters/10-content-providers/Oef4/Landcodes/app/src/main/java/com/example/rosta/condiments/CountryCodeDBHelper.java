package com.example.rosta.condiments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rosta on 11/2/16.
 */

public class CountryCodeDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "countrycode.db";

    // Deze constructor copy pasten naar elk project, niet de default die aangemaakt wordt hanteren
    // MovieDBHelper aanpassen naar naam van eigen project..
    public CountryCodeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Wordt aangeroepen wanneer de database voor de eerste keer aangemaakt wordt.
     *
     * db is de database waarop alle SQL-statements uitgevoerd zullen worden.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        addCountryCodeTable(db);
    }

    private void addCountryCodeTable(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + CountryCodeContract.CountryCodeEntry.TABLE_NAME + " (" +
                        CountryCodeContract.CountryCodeEntry._ID + " INTEGER PRIMARY KEY, " +
                        CountryCodeContract.CountryCodeEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                        CountryCodeContract.CountryCodeEntry.COLUMN_CODE + " TEXT UNIQUE NOT NULL, " +
                        CountryCodeContract.CountryCodeEntry.COLUMN_NUMBER + " REAL UNIQUE);"
        );

    }

    /**
     * Dit is niet per se de juiste aanpak, ik drop de tabel en maak een nieuwe aan..
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        upgradeCountryCodeTable(db);

    }

    private void upgradeCountryCodeTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + CountryCodeContract.CountryCodeEntry.TABLE_NAME);
        onCreate(db);
    }

}
