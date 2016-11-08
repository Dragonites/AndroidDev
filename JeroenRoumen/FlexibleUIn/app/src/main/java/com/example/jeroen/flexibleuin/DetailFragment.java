package com.example.jeroen.flexibleuin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Jeroen on 07/11/2016.
 */

public class DetailFragment extends Fragment {
    EditText etFirstName, etLastName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false); //inflate van detail_fragment.xml
        etFirstName = (EditText) view.findViewById(R.id.editTextFirstname);
        etLastName = (EditText) view.findViewById(R.id.editTextLastname);
        return view;
    }

    public void setFirstAndLastName(String firstname, String lastname) {
        etFirstName.setText(firstname);
        etLastName.setText(lastname);
    }
}
