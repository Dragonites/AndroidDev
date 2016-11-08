package com.example.jeroen.restaurantbestelling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dagSelectieClick(View view) {
        String tekst = ((Button)view).getText().toString();
        Intent intent = new Intent(this, DaySelectionActivity.class);
        intent.putExtra("tekst", tekst);
        startActivity(intent);
    }
}
