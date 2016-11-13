package com.example.rosta.elections2016;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Rosta on 11-11-2016.
 */
public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        // Ophalen van de ListView zodat we custom adapter kunnen zetten (onderaan)
        ListView listView = (ListView) view.findViewById(R.id.list_fragment);

        String[] candidates = getResources().getStringArray(R.array.names);
        String[] parties = getResources().getStringArray(R.array.parties);

        MySimpleArrayAdapter cumstomAdapter = new MySimpleArrayAdapter(getActivity(), candidates, parties);
        listView.setAdapter(cumstomAdapter);

        // Deze code pas toevoegen wanneer je het tegenkomt in het stappenplan (DetailListener)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Wanneer er een rij aangeklikt wordt, sturen we de positie door naar de Listener
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailListener detailListener = (DetailListener) getActivity();
                detailListener.setFieldsAndPicture(position);
            }
        });
        // Tot hier (DetailListener)

        return view;
    }
}
