package com.example.rosta.elections2016.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.rosta.elections2016.Candidate.Candidate;
import com.example.rosta.elections2016.Candidate.ElectionUtils;

import java.util.List;

/**
 * Created by Rosta on 13-11-2016.
 */

public class CandidateDBHelper extends SQLiteOpenHelper {

    // Context is nodig om de candidats op te halen via de Util-klasse
    private Context context;

    // Deze Constanten zijn niet nodig, maar gebruik ik omdat ik in mijn
    // andere opdrachten hier altijd de versie en naam bepaalde.
    // Ik haal ze op uit het aangeleverde Contract.
    private static final int DATABASE_VERSION = ElectionContract.DB_VER;
    private static final String DATABASE_NAME = ElectionContract.DB_NAME;

    // Deze constructor copy pasten naar elk project, niet de default die aangemaakt wordt hanteren
    // CandidateDBHelper aanpassen naar naam van eigen project..
    public CandidateDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * Wordt aangeroepen wanneer de database voor de eerste keer aangemaakt wordt.
     *
     * db is de database waarop alle SQL-statements uitgevoerd zullen worden.
     *
     * DIT IS WAAR JE DATA KAN SEEDEN!
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Maak database
        addCandidateTable(db);
        // Voorzie data in database
//        seedCandidateTable();
    }

    private void addCandidateTable(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + ElectionContract.Candidates.TABLE_NAME + " (" +
                        ElectionContract.Candidates._ID + " INTEGER PRIMARY KEY, " +
                        ElectionContract.Candidates.COL_NAME + " TEXT NOT NULL, " +
                        ElectionContract.Candidates.COL_PARTY + " TEXT NOT NULL, " +
                        ElectionContract.Candidates.COL_AGE + " INTEGER NOT NULL, " +
                        ElectionContract.Candidates.COL_VOTES + " INTEGER NOT NULL);"
        );
    }

    private void seedCandidateTable() {

            List<Candidate> candidates = ElectionUtils.getCandidates(context);
            ContentValues values = new ContentValues();
            ContentResolver resolver = context.getContentResolver();

            for (Candidate c : candidates) {
                values.put(ElectionContract.Candidates.COL_NAME, c.getName());
                values.put(ElectionContract.Candidates.COL_PARTY, c.getParty());
                values.put(ElectionContract.Candidates.COL_AGE, c.getAge());
                values.put(ElectionContract.Candidates.COL_VOTES, c.getVotes());
                resolver.insert(ElectionContract.Candidates.CONTENT_URI, values);
            }

    }



    /**
     * Dit is niet per se de juiste aanpak, ik drop de tabel en maak een nieuwe aan..
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        upgradeCandidateTable(db);
    }

    private void upgradeCandidateTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + ElectionContract.Candidates.TABLE_NAME);
        onCreate(db);
    }
}
