package com.example.jeroen.fragmentdemo2;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FragmentA extends Fragment {

    private final String TAG = FragmentA.class.getSimpleName();

    private Button buttonAdd;
    private TextView txtView;

    private int firstNumber = 0, secondNumber = 0;

    private MainActivity.Employee employee;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        /*verkrijgen van de bundle waarden die zijn meegegeven
          de 0 geeft de defaultwaarde aan zodat het programma niet crashed*/

        txtView = (TextView) view.findViewById(R.id.resultView);
        buttonAdd = (Button) view.findViewById(R.id.addButton);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTwoNumbers(firstNumber, secondNumber);
            }
        });

        return view;
    }

    private void AddTwoNumbers(int fNumber, int sNumber) {
        int result = fNumber + sNumber;
        txtView.setText("Result: " + result);
    }


    public void setData(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public void setEmployeeObj(MainActivity.Employee employee) {
        this.employee = employee;
    }
}
