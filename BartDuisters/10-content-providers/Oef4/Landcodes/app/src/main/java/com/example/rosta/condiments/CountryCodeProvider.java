package com.example.rosta.condiments;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by rosta on 11/3/16.
 */

public class CountryCodeProvider extends ContentProvider {
    
    // Gebruik een int voor elke URI die je wilt runnen, dit stellen de verschillende queries voor
    private static final int COUNTRY = 100;
    private static final int COUNTRY_ID = 101;

    // SQLiteOpenHelper om toegang tot de database te krijgen, setter in onCreate();
    private CountryCodeDBHelper mOpenHelper;

    // UriMatcher zal een URI binnenkrijgen en bepalen welke Integer die hierboven gedefinieerd wordt, overeenkomt
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        String content = CountryCodeContract.CONTENT_AUTHORITY;

        // Alle paden van de UriMatcher hebben een overeenkomende code om terug te geven wanneer er een match gevonden wordt (Integers hierboven)
        // # = eender welk getal
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(content, CountryCodeContract.PATH_CONDIMENT, COUNTRY);
        matcher.addURI(content, CountryCodeContract.PATH_CONDIMENT + "/#", COUNTRY_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new CountryCodeDBHelper(getContext());
        return true;
    }

    /**
     * getType wordt gebruikt om de MIME-type van het resultaat te bepalen.
     * Ofwel is het een directory van meerdere resultaten ofwel is het een individueel item.
     */
    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case COUNTRY:
                return CountryCodeContract.CountryCodeEntry.CONTENT_TYPE;
            case COUNTRY_ID:
                return CountryCodeContract.CountryCodeEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }

    /**
     * Deze moet minstens geïmplementeerd zijn voor de oefening 'Condiments'
     *
     * Op basis van welke Integer gematched wordt, sturen we queries naar de bijhorende tabel
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long _id;
        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case COUNTRY:
                retCursor = db.query(
                        CountryCodeContract.CountryCodeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case COUNTRY_ID:
                _id = ContentUris.parseId(uri);
                retCursor = db.query(
                        CountryCodeContract.CountryCodeEntry.TABLE_NAME,
                        projection,
                        CountryCodeContract.CountryCodeEntry._ID + " = ?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unkown URI: " + uri);
        }

        // setNotificationUri wordt gelijk gezet aan diegene die doorgegeven wordt aan de functie.
        // Dit zorgt ervoor dat de cursor een Content Observer registreert die veranderingen aan
        // de URI en alle URI's die beginnen met dit pad opmerkt.
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;

    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        switch(sUriMatcher.match(uri)){
            case COUNTRY:
                _id = db.insert(CountryCodeContract.CountryCodeEntry.TABLE_NAME, null, values);
                if(_id > 0){
                    returnUri =  CountryCodeContract.CountryCodeEntry.buildCondimentUri(_id);
                } else{
                    throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Dit zorgt ervoor dat Observers (zie query-methode) op de hoogte worden gebracht van de verandering aan deze URI
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows; // Aantal rijen die beïnvloedt worden door deze handeling

        switch(sUriMatcher.match(uri)){
            case COUNTRY:
                rows = db.delete(CountryCodeContract.CountryCodeEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Null zou alle rijen kunnen wissen
        if(selection == null || rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows; // Aantal rijen die beïnvloedt worden door deze handeling

        switch(sUriMatcher.match(uri)){
            case COUNTRY:
                rows = db.update(CountryCodeContract.CountryCodeEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }
}
