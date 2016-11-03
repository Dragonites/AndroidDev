package com.example.rosta.condiments;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * UNCOMMENT DEZE REGEL 1X
         * Wanneer je het 1x hebt laten runnen, comment je deze regel!!!
         *
         * Dit voegt de nodige data van de oefening toe, maar hier zit een UNIQUE restraint op,
         * de app zal dus niet opstarten wanneer je dit een 2e x probeert te runnen.
         */
//        setData();


        List<String> condiments = new ArrayList<>();
        List<String> prices = new ArrayList<>();

        ContentResolver resolver = getContentResolver();
        String[] projection = new String[]{CondimentsContract.CondimentEntry.COLUMN_NAME, CondimentsContract.CondimentEntry.COLUMN_PRICE};

        Cursor cursor = resolver.query(CondimentsContract.CondimentEntry.CONTENT_URI, projection,
                null, null, null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String condiment = cursor.getString(0);
            String price = cursor.getString(1);

            condiments.add(condiment);
            prices.add(price);

            cursor.moveToNext();
        }

        MySimpleArrayAdapter customAdapter = new MySimpleArrayAdapter(getApplicationContext(), condiments, prices);
        setListAdapter(customAdapter);

    }

    private void setData() {

        ContentValues values = new ContentValues();
        values.put(CondimentsContract.CondimentEntry.COLUMN_NAME, "Spaghetti");
        values.put(CondimentsContract.CondimentEntry.COLUMN_PRICE, 11);

        ContentResolver resolver = getContentResolver();
        resolver.insert(CondimentsContract.CondimentEntry.CONTENT_URI, values);

        values.put(CondimentsContract.CondimentEntry.COLUMN_NAME, "Fries");
        values.put(CondimentsContract.CondimentEntry.COLUMN_PRICE, 5);
        resolver.insert(CondimentsContract.CondimentEntry.CONTENT_URI, values);

        values.put(CondimentsContract.CondimentEntry.COLUMN_NAME, "Water");
        values.put(CondimentsContract.CondimentEntry.COLUMN_PRICE, 1);
        resolver.insert(CondimentsContract.CondimentEntry.CONTENT_URI, values);

        values.put(CondimentsContract.CondimentEntry.COLUMN_NAME, "Beer");
        values.put(CondimentsContract.CondimentEntry.COLUMN_PRICE, 2);
        resolver.insert(CondimentsContract.CondimentEntry.CONTENT_URI, values);

        values.put(CondimentsContract.CondimentEntry.COLUMN_NAME, "Soda");
        values.put(CondimentsContract.CondimentEntry.COLUMN_PRICE, 2.5);
        resolver.insert(CondimentsContract.CondimentEntry.CONTENT_URI, values);

    }
}
