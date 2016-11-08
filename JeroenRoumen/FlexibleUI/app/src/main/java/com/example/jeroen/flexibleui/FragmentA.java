package com.example.jeroen.flexibleui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Jeroen on 05/11/2016.
 */

public class FragmentA extends Fragment {

    String fn, ln;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //load view fragment_a
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        //get resources from string resource file
        String[] namen = getResources().getStringArray(R.array.namenLijst);

        //set adapter to list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, namen);
        ListView listView = (ListView) view.findViewById(R.id.lvNamen);

        //itemClickListener event handler to send data to fragment B
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sendPosition(position);
            }
        });

        listView.setAdapter(adapter);
        return view;
    }

    public void sendPosition(int position) {
        MyListener myListener = (MyListener) getActivity();
        myListener.sendFirstLastName(position);
    }


}
