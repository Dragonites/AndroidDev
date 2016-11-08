package com.example.jeroen.customoslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jeroen on 18/10/2016.
 */

public class OperatingSystemsAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    //Constructor inherited from ArrayAdapter
    public OperatingSystemsAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textRow);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageRow);
        textView.setText(values[position]);
        //check if the value contains an Apple OS
        String s = values[position];
        if (s.contains("OS")) {
            imageView.setImageResource(R.mipmap.ic_apple);
        } else {
            imageView.setImageResource(R.drawable.linux);
        }
        return rowView;
    }
}
