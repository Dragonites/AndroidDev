package com.example.jeroen.checkedoslist;

import android.app.ListActivity;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] osList = getResources().getStringArray(R.array.osList);
        MyArrayAdapter adapter = new MyArrayAdapter(this, osList);
        setListAdapter(adapter);
    }
}
