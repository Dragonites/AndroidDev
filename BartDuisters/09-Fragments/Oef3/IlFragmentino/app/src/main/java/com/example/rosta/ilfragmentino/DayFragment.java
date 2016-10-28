package com.example.rosta.ilfragmentino;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rosta on 10/28/16.
 */

public class DayFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.day_fragment, container, false);

        TextView textView = (TextView) view.findViewById(R.id.dayField);
        textView.setText(getArguments().getString("day"));
        return view;
    }

}
