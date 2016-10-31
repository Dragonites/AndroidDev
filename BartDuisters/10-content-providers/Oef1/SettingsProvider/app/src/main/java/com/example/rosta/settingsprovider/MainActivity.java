package com.example.rosta.settingsprovider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    /**
     * !!! README !!!
     * Wanneer je alles van 'normale ArrayAdapter' uncomment, moet je alles van 'custom ArrayAdapter' in comments zetten.
     * Wanneer je alles van 'custom ArrayAdapter' uncomment, moet je alles van 'normale ArrayAdapter' in comments zetten.
     */

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
        //        List<String> nameValues = new ArrayList<>();
        /** */

        /**
         * Deze code uncommenten om een custom ArrayAdapter te gebruiken
         *
         * maakt gebruikt van MySimpleArrayAdapter.java en rowlayout.xml
         */
        List<String> names = new ArrayList<String>();
        List<String> values = new ArrayList<String>();

        // Werken via ContentResolver, niet direct op ContentProvider
        ContentResolver resolver = getContentResolver();
        // De gegevens die je wilt opvragen
        String[] projection = new String[]{Settings.System.NAME, Settings.System.VALUE};
        // Vraag de gegevens op
        Cursor cursor =
                resolver.query(Settings.System.CONTENT_URI, projection,
                        null,
                        null,
                        null);
        // Ga naar de eerste row
        cursor.moveToFirst();
        //Zolang er rows zijn...
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            String value = cursor.getString(1);

            /**
             * Deze code uncommenten om normale ArrayAdapter te gebruiken
             */
//            String result = name + "\n" + value;
//            nameValues.add(result);
            /** */

            /**
             * Deze code uncommenten om custom ArrayAdapter te gebruiken
             */
            names.add(name);
            values.add(value);
            /** */

            cursor.moveToNext();
        }

        /**
         * Deze code uncommenten om normale ArrayAdapter te gebruiken
         */
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, nameValues);
//        setListAdapter(adapter);
        /** */

        /**
         * Deze code uncommenten om custom ArrayAdapter te gebruiken
         */
        MySimpleArrayAdapter customAdapter = new MySimpleArrayAdapter(getApplicationContext(), names, values);
        setListAdapter(customAdapter);
        /** */
    }
}
