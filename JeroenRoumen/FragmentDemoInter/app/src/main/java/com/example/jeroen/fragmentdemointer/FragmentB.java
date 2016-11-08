package com.example.jeroen.fragmentdemointer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Jeroen on 05/11/2016.
 */

public class FragmentB extends Fragment {

    private static final String TAG = FragmentB.class.getSimpleName();

    private TextView txvResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        txvResult = (TextView) view.findViewById(R.id.txvResult);
        return view;
    }

    public void addTwoNumbersInFragmentB(int x, int y) {
        int result = x + y;
        txvResult.setText("Result : " + result);
    }
}
