package com.example.jeroen.checkedoslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.CompoundButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeroen on 27/10/2016.
 */

public class MyArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final String[] values;
    private List<String> checkboxes = new ArrayList<>();

    public MyArrayAdapter(Context context, String[] values) {
        super(context, R.layout.custom_row, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_row, parent, false);
        final TextView textView = (TextView) rowView.findViewById(R.id.osLabel);
        final CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.osCheckBox);
        //logic to add a checked checkbox to the list
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (checkBox.isChecked()) {
                    checkboxes.add(textView.getText().toString());
                } else {
                    checkboxes.remove(textView.getText().toString());
                }

                String toPrint = "Checked:\n";
                for (String s : checkboxes) {
                    toPrint += "\n" + s;
                }

                Toast toast = Toast.makeText(getContext(), toPrint, Toast.LENGTH_SHORT);
                toast.show();
            }

        });
        textView.setText(values[position]);
        return rowView;
        }
}
