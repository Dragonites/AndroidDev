package com.example.rosta.condiments;

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
 * Created by rosta on 11/3/16.
 */

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> lands;
    private final List<String> codes;
    private final List<String> numbers;

    public MySimpleArrayAdapter(Context context, List<String> lands, List<String> codes, List<String> numbers) {
        super(context, -1, lands);
        this.context = context;
        this.lands = lands;
        this.codes = codes;
        this.numbers = numbers;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.land);
        TextView textViewCode = (TextView) rowView.findViewById(R.id.landcode);
        TextView textViewNumber = (TextView) rowView.findViewById(R.id.landnumber);

        textViewName.setText(lands.get(position));
        textViewCode.setText(codes.get(position));
        textViewNumber.setText(numbers.get(position));

        // Change background color even/odd
        if (position%2 == 0) {
            rowView.setBackgroundColor(Color.parseColor("#4f81bd"));
        } else {
            rowView.setBackgroundColor(Color.parseColor("#ff0000"));
        }

        return rowView;
    }
}
