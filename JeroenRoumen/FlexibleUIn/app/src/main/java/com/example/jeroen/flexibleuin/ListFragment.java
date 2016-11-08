package com.example.jeroen.flexibleuin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Jeroen on 07/11/2016.
 */

public class ListFragment extends Fragment {
    String fn, ln;
    MyListener myListener;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false); //view inflaten
        listView = (ListView) view.findViewById(R.id.lvNames); //listview ophalen

        //onClickListener koppelen aan de listview en namen doorgeven
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myListener = (MyListener) getActivity();
                String[] names = (listView.getItemAtPosition(position)).toString().split(" ");
                myListener.setNames(names[0], names[1]);
            }
        });

        String[] names = getResources().getStringArray(R.array.names_array); //string array namen ophalen resources
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), //adapter instellen (MainActivity ophalen, wat voor soort layout, lijst namen)
                android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
        return view;
    }

}
