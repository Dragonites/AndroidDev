package com.example.rosta.flexibleui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by rosta on 10/27/16.
 */
public class ListFragment extends Fragment {

    ListView listView;
    ArrayAdapter<String> adapter;
    String[] names;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        listView = (ListView) view.findViewById(R.id.list_fragment);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NameListener nameListener = (NameListener) getActivity();
                nameListener.setNames(position);
            }
        });

        names = getResources().getStringArray(R.array.names);

        // Uncomment eerste adapter indien je een custom list view layout en custom row layout wilt gebruiken
        // Uncomment tweede adapter voor een standaard list view layout
//        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_view_layout, R.id.row_item, names);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        return view;
    }

}
