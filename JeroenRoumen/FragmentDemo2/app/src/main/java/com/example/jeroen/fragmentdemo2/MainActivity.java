package com.example.jeroen.fragmentdemo2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private FragmentManager manager;
    private EditText firstNumber, secondNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getFragmentManager();
        firstNumber = (EditText) findViewById(R.id.etFirstNumber);
        secondNumber = (EditText) findViewById(R.id.etSecondNumber);
    }

    public void sendDataToFragmentA(View view) {
        int fNumber = Integer.valueOf(firstNumber.getText().toString());
        int sNumber = Integer.valueOf(secondNumber.getText().toString());

        FragmentA fragmentA = new FragmentA();
        fragmentA.setData(fNumber, sNumber); //primitive datatype method
        fragmentA.setEmployeeObj(new Employee()); //object datatype method

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.containterFragmentA, fragmentA, "fragA");
        transaction.commit();
    }

    public class Employee {
        String firstName;
        String lastName;

        public Employee() {}

        public Employee(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

    }
}
