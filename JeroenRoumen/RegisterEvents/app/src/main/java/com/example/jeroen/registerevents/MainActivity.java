package com.example.jeroen.registerevents;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected boolean userPassOk = true;
    protected String foutmelding = "";
    protected boolean bevatCijfer = false;
    protected boolean bevatLetter = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Give login button event handler
        Button btn = (Button)findViewById(R.id.loginButton);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText edt = (EditText)findViewById(R.id.usernameBox);
        String username = (edt.getText()).toString();
        String password = "";
        if(username.length() < 5) {
            userPassOk = false;
        }
        edt = (EditText)findViewById(R.id.passwordBox);
        password = (edt.getText()).toString();

        //show values in a toast
        Context context = getApplicationContext();
        CharSequence text;
        if (userPassOk == false) {
            foutmelding = "Username moet minstens 5 tekens bevatten\n";
        }

        if (password.length() < 5) {
            foutmelding += "Wachtwoord moet minstens 5 tekens bevatten\n";
        }
        //lussen door wachtwoord, kijken of cijfer en letter bevat
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isLetter(c)) {
                bevatLetter = true;
            } else if (Character.isDigit(c)) {
                bevatCijfer = true;
            }

        }

        if (bevatCijfer == false) {
            foutmelding += "Het wachtwoord bevat geen cijfer\n";
        } else if (bevatLetter == false) {
            foutmelding += "Het wachtwoord bevat geen letter\n";
        }

        if (userPassOk  && bevatCijfer && bevatLetter) {
            foutmelding = "Wachtwoord en username in orde";
        }

        text = foutmelding;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        userPassOk = true;
        bevatCijfer = false;
        bevatLetter = false;

    }
}
