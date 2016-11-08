package com.example.jeroen.royalty;

import android.app.ListActivity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {
    String[] vorstenNL;
    String[] vorstenEN;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = getResources();
        vorstenNL = res.getStringArray(R.array.dutch_leaders);
        vorstenEN = res.getStringArray(R.array.english_leaders);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, vorstenNL);
        setListAdapter(adapter);
        registerForContextMenu(getListView());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.select_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) menuInfo;
        String selectedWord = ((TextView) info.targetView).getText().toString();
        menu.setHeaderTitle(selectedWord);
        getMenuInflater().inflate(R.menu.menu , menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.labelNL:
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, vorstenNL);
                setListAdapter(adapter);
                break;
            case R.id.labelEN:
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, vorstenEN);
                setListAdapter(adapter);
                break;
        }
        return true;
    }

}
