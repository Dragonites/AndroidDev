package com.example.rosta.settingsprovider;

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
    private final List<String> names;
    private final List<String> values;

    public MySimpleArrayAdapter(Context context, List<String> names, List<String> values) {
        super(context, -1, values);
        this.context = context;
        this.names = names;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.name);
        TextView textViewValue = (TextView) rowView.findViewById(R.id.value);

        textViewName.setText(names.get(position));
        textViewValue.setText(values.get(position));

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
