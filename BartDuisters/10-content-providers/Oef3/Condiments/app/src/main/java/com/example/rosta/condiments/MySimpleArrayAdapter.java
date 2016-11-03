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
    private final List<String> condiments;
    private final List<String> prices;

    public MySimpleArrayAdapter(Context context, List<String> condiments, List<String> prices) {
        super(context, -1, condiments);
        this.context = context;
        this.condiments = condiments;
        this.prices = prices;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.condiment);
        TextView textViewValue = (TextView) rowView.findViewById(R.id.price);

        textViewName.setText(condiments.get(position));
        textViewValue.setText(prices.get(position));

        // Change background color even/odd
//        if (position%2 == 0) {
//            rowView.setBackgroundColor(Color.parseColor("#4f81bd"));
//        } else {
//            rowView.setBackgroundColor(Color.parseColor("#ff0000"));
//        }

        return rowView;
    }
}
