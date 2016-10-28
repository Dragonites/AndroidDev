package com.example.rosta.ilfragmentino;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PeriodListener, DayListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new PeriodFragment());
        ft.commit();
    }

    // Controleer welke Button werd geklikt in PeriodFragment
    public void periodButtonClicked(View view) {
        /**
         * De code in comments werkt ook wanneer je met ID wilt werken.
         * Ik werk met TAGS omdat de code op deze manier korter is,
         * zeker wanneer je meerdere opties hebt (zoals in WeekFragment).
         */
//        Button button = (Button) view;
//        if (view.findViewById(R.id.period1) != null) {
//            button = (Button) view.findViewById(R.id.period1);
//        }
//        if (view.findViewById(R.id.period2) != null) {
//            button = (Button) view.findViewById(R.id.period2);
//        }
//        if (view.findViewById(R.id.period3) != null) {
//            button = (Button) view.findViewById(R.id.period3);
//        }
//        String week = button.getText().toString();
//        passPeriod(week);

        Button button;
        button = (Button) view.findViewWithTag("button");
        Toast.makeText(this, button.getText().toString(), Toast.LENGTH_SHORT).show();
        String week = button.getText().toString();
        passPeriod(week);
    }

    // Maakt een WeekFragment aan en geeft de waarde door via SetArguments (Bundle)
    @Override
    public void passPeriod(String week) {
        Bundle bundle = new Bundle();
        bundle.putString("week", week);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        WeekFragment weekFragment = new WeekFragment();
        weekFragment.setArguments(bundle);

        ft.replace(R.id.fragment_container, weekFragment);
        ft.commit();
    }


    // Controleer welke Button werd geklikt in WeekFragment
    public void weekButtonClicked(View view) {
        Button button;
        button = (Button) view.findViewWithTag("button");
        Toast.makeText(this, button.getText().toString(), Toast.LENGTH_SHORT).show();
        String day = button.getText().toString();
        passDay(day);
    }

    @Override
    public void passDay(String day) {
        Bundle bundle = new Bundle();
        bundle.putString("day", day);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        DayFragment dayFragment = new DayFragment();
        dayFragment.setArguments(bundle);

        ft.replace(R.id.fragment_container, dayFragment);
        ft.commit();
    }
}
