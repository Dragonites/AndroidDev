package com.example.jeroen.flexibleui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Jeroen on 06/11/2016.
 */
public class FragmentB extends Fragment {

    private EditText etFirstName, etLastName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        etFirstName = (EditText) view.findViewById(R.id.firstnameEditText);
        etLastName = (EditText) view.findViewById(R.id.lastnameEditText);
        return view;
    }

    public void setFirstLastNameInFragmentB(String f, String l) {
        etFirstName.setText(f);
        etLastName.setText(l);
    }
}
