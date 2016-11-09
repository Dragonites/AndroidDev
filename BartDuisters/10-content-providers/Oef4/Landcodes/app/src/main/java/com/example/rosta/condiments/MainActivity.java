package com.example.rosta.condiments;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rosta.condiments.Country.Country;
import com.example.rosta.condiments.Country.CountryData;

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
         *
         * In het examen kan je dit best toevoegen aan de onCreate van DBHelper (deze runt maar 1x en zal dus nooit de error opleveren)
         */
        //setData();

        List<String> lands = new ArrayList<>();
        List<String> codes = new ArrayList<>();
        List<String> numbers = new ArrayList<>();


        ContentResolver resolver = getContentResolver();
        String[] projection = new String[]{CountryCodeContract.CountryCodeEntry.COLUMN_NAME, CountryCodeContract.CountryCodeEntry.COLUMN_CODE, CountryCodeContract.CountryCodeEntry.COLUMN_NUMBER};

        Cursor cursor = resolver.query(CountryCodeContract.CountryCodeEntry.CONTENT_URI, projection,
                null, null, null);


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String land = cursor.getString(0);
            String code = cursor.getString(1);
            String number = cursor.getString(2);

            lands.add(land);
            codes.add(code);
            numbers.add(number);

            cursor.moveToNext();
        }

        MySimpleArrayAdapter customAdapter = new MySimpleArrayAdapter(getApplicationContext(), lands, codes, numbers);
        setListAdapter(customAdapter);

    }

    private void setData() {

        List<Country> countries = CountryData.getCountryList();
        ContentValues values = new ContentValues();
        ContentResolver resolver = getContentResolver();

        for (Country c : countries) {
            //Toast gebruikt om te kijken wat de output was van elke getter
//            Toast.makeText(this, "name: " + c.getName() + "\ndisplayName: " + c.getDisplayName() + "\niso2: " + c.getIso2() + "\niso3 :" + c.getIso3() + "\nnumCode: " + c.getNumCode(), Toast.LENGTH_LONG).show();
            values.put(CountryCodeContract.CountryCodeEntry.COLUMN_NAME, c.getDisplayName());
            values.put(CountryCodeContract.CountryCodeEntry.COLUMN_CODE, c.getIso2());
            values.put(CountryCodeContract.CountryCodeEntry.COLUMN_NUMBER, c.getNumCode());
            resolver.insert(CountryCodeContract.CountryCodeEntry.CONTENT_URI, values);
        }

    }
}
