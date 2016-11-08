package com.example.jeroen.restaurantbestelling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class DaySelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_selection);
        //waarden van intent gebruiken
        String tekst = getIntent().getStringExtra("tekst");
        TextView edt = (TextView) findViewById(R.id.tekstdoos);
        edt.setText(tekst);
    }

    public void voedingDetailClick(View view) {
        Intent intent = new Intent(this, VoedselDetailActivity.class);
        startActivity(intent);
    }
}
