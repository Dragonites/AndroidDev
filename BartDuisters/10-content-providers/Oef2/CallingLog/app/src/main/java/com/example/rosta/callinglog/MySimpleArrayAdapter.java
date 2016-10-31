package com.example.rosta.callinglog;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rosta on 10/31/16.
 */

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> numbers;
    private final List<String> durations;

    public MySimpleArrayAdapter(Context context, List<String> numbers, List<String> durations) {
        super(context, -1, durations);
        this.context = context;
        this.numbers = numbers;
        this.durations = durations;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.number);
        TextView textViewValue = (TextView) rowView.findViewById(R.id.duration);

        textViewName.setText(numbers.get(position));
        textViewValue.setText(durations.get(position));

        // Change background color even/odd
//        String s = values[position];
        if (position%2 == 0) {
            rowView.setBackgroundColor(Color.parseColor("#4f81bd"));
        } else {
            rowView.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        return rowView;
    }
}
