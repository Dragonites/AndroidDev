package com.example.jeroen.restaurantlistview;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DayActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        String[] dagen = res.getStringArray(R.array.dagen);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.week_row_layout, R.id.labelDag, dagen);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        String tekst = (l.getItemAtPosition(position)).toString();
        Toast toast = Toast.makeText(context, tekst, duration);
        toast.show();
    }
}
