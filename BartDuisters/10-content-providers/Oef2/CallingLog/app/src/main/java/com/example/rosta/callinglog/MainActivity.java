package com.example.rosta.callinglog;

import android.Manifest;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
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

        // Lijst om gegevens in op te slaan

        /**
         * Deze code uncommenten om een normale ArrayAdapter te gebruiken
         *
         * kan gebruikt worden zonder MySimpleArrayAdapter.java en rowlayout.xml
         */
//        List<String> numbersDurations = new ArrayList<>();
        /** */

        /**
         * Deze code uncommenten om een custom ArrayAdapter te gebruiken
         *
         * maakt gebruikt van MySimpleArrayAdapter.java en rowlayout.xml
         */
        List<String> numbers = new ArrayList<String>();
        List<String> durations = new ArrayList<String>();

        // Werken via ContentResolver, niet direct op ContentProvider
        ContentResolver resolver = getContentResolver();
        // De gegevens die je wilt opvragen
        String[] projection = new String[]{CallLog.Calls.NUMBER, CallLog.Calls.DURATION};

        // Controleren of de permissions aanwezig zijn
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        // Vraag de gegevens op
        Cursor cursor =
                resolver.query(CallLog.Calls.CONTENT_URI, projection,
                        null,
                        null,
                        null);
        // Ga naar de eerste row
        cursor.moveToFirst();
        // Zolang er rows zijn...
        while (!cursor.isAfterLast()) {
            String number = cursor.getString(0);
            String duration = cursor.getString(1);

            /**
             * Deze code uncommenten om normale ArrayAdapter te gebruiken
             */
//            String result = number + "\n" + duration;
//            numbersDurations.add(result);
            /** */

            /**
             * Deze code uncommenten om custom ArrayAdapter te gebruiken
             */
            numbers.add(number);
            durations.add(duration);
            /** */

            cursor.moveToNext();
        }

        /**
         * Deze code uncommenten om normale ArrayAdapter te gebruiken
         */
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, numbersDurations);
//        setListAdapter(adapter);
        /** */

        /**
         * Deze code uncommenten om custom ArrayAdapter te gebruiken
         */
        MySimpleArrayAdapter customAdapter = new MySimpleArrayAdapter(getApplicationContext(), numbers, durations);
        setListAdapter(customAdapter);
        /** */

    }
}
