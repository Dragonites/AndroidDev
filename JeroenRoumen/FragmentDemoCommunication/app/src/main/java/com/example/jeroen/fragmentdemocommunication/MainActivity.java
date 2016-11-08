package com.example.jeroen.fragmentdemocommunication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyListener {
    private String TAG = MainActivity.class.getSimpleName();

    private FragmentManager manager;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getFragmentManager();
        txtResult = (TextView) findViewById(R.id.resultView);
        
        addFragmentA();
    }

    private void addFragmentA() {
        FragmentA fragmentA = new FragmentA();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.containterFragmentA, fragmentA, "fragA");
        transaction.commit();
    }

    @Override
    public void add(int num1, int num2) {
        String result = Integer.toString(num1 + num2);
        txtResult.setText(result);
    }
}
