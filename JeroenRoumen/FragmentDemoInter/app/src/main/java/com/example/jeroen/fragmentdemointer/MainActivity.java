package com.example.jeroen.fragmentdemointer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyListener {
    private String TAG = MainActivity.class.getSimpleName();

    private FragmentManager manager;
    private TextView txtResult;

    private int firstNum = 0, secondNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getFragmentManager();
        txtResult = (TextView) findViewById(R.id.txvResult);

        //toevoegen van de fragments op de hoofdactiviteit
        addFragmentA();
        addFragmentB();
    }

    private void addFragmentA() {
        FragmentA fragmentA = new FragmentA();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.containterFragmentA, fragmentA, "fragA");
        transaction.commit();
    }

    private void addFragmentB() {
        FragmentB fragmentB = new FragmentB();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.containerFragmentB, fragmentB, "fragB");
        transaction.commit();
    }

    @Override
    public void addTwoNumbers(int num1, int num2) {
        this.firstNum = num1;
        this.secondNum = num2;

        Toast.makeText(this, "Data has been received: " + this.firstNum +
                " and " + this.secondNum , Toast.LENGTH_SHORT);
    }

    public void sendDataToFragmentB(View view) {
        //finding the fragment by Tag
        FragmentB fragmentB = (FragmentB) manager.findFragmentByTag("fragB");
        fragmentB.addTwoNumbersInFragmentB(firstNum, secondNum);

    }
}
