package com.example.jeroen.optionsmenuvsactionbar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast;
        switch (item.getItemId()) {
            case R.id.create:
                toast = Toast.makeText(context, "Create", duration);
                toast.show();
            case R.id.update:
                toast = Toast.makeText(context, "Update", duration);
                toast.show();
                return true;
            case R.id.delete:
                toast = Toast.makeText(context, "Delete", duration);
                toast.show();
            case R.id.delete2:
                toast = Toast.makeText(context, "Delete2", duration);
                toast.show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
