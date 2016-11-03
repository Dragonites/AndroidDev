package com.example.rosta.condiments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rosta on 11/2/16.
 */

public class CondimentsDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movielist.db";

    // Deze constructor copy pasten naar elk project, niet de default die aangemaakt wordt hanteren
    // MovieDBHelper aanpassen naar naam van eigen project..
    public CondimentsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Wordt aangeroepen wanneer de database voor de eerste keer aangemaakt wordt.
     *
     * db is de database waarop alle SQL-statements uitgevoerd zullen worden.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        addCondimentTable(db);
    }

    private void addCondimentTable(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + CondimentsContract.CondimentEntry.TABLE_NAME + " (" +
                        CondimentsContract.CondimentEntry._ID + " INTEGER PRIMARY KEY, " +
                        CondimentsContract.CondimentEntry.COLUMN_NAME + " TEXT UNIQUE NOT NULL, " +
                        CondimentsContract.CondimentEntry.COLUMN_PRICE + " REAL);"
        );

    }

    /**
     * Dit is niet per se de juiste aanpak, ik drop de tabel en maak een nieuwe aan..
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        upgradeCondimentTable(db);

    }

    private void upgradeCondimentTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + CondimentsContract.CondimentEntry.TABLE_NAME);
        onCreate(db);
    }

}
