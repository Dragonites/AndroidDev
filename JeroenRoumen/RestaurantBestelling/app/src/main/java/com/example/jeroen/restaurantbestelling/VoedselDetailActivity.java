package com.example.jeroen.restaurantbestelling;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class VoedselDetailActivity extends AppCompatActivity {
    List<CheckBox> checkboxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voedsel_detail);
        checkboxes = new ArrayList<>();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.confirm_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        String tekst = "";
        for (CheckBox cb : checkboxes) {
            tekst += cb.getText();
        }
        Toast toast = Toast.makeText(context, tekst, duration);
        toast.show();
        return true;
    }

    public void onCheckboxClicked(View view) {
        CheckBox checkbox = (CheckBox)view;
        checkboxes.add(checkbox);
    }
}
