package com.example.rosta.loginlineair;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.System.in;

public class MainActivity extends AppCompatActivity {

    // Bestandnaam waarin de gegevens komen die we willen bijhouden
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonOk = (Button) findViewById(R.id.buttonLogin);
        buttonOk.setOnClickListener(buttonLoginListener);
    }

    // Enkel onResume() en onPause() zijn nieuw in deze opdracht!
    @Override
    protected void onResume() {
        super.onResume();

        // Nodig om gegevens op te slaan
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);


        EditText editTextUsername = (EditText) findViewById(R.id.editText);
        EditText editTextPassword = (EditText) findViewById(R.id.editText2);

        // Vraag de 'value' op, opgeslagen in values 'username' en 'password'
        // Indien deze niet bestaan, vul je 'n/a' in
        editTextUsername.setText(settings.getString("username", "n/a"));
        editTextPassword.setText(settings.getString("password", "n/a"));

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        EditText editTextUsername = (EditText) findViewById(R.id.editText);
        EditText editTextPassword = (EditText) findViewById(R.id.editText2);

        // Sla de huidige waarde in username en paswoord op in de keys
        // 'username' en 'password'
        editor.putString("username", editTextUsername.getText().toString());
        editor.putString("password", editTextPassword.getText().toString());

        // apply() i.p.v. commit() om het in de background te doen
        editor.apply();
    }

    private OnClickListener buttonLoginListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText username = (EditText) findViewById(R.id.editText);
            CharSequence usernameText = username.getText();

            EditText password = (EditText) findViewById(R.id.editText2);
            CharSequence passwordText = password.getText();

            CharSequence text = "";

            if (usernameText.length() < 5) {
                text = text + "Username moet min. 5 karakters bevatten.";
            }

            if (passwordText.length() < 5) {
                text = text + "Paswoord moet min. 5 karakters bevatten.";
            }

            boolean containsDigit = false;
            boolean containsLetter = false;

            for (int i = 0; i < passwordText.length(); i++) {

                if (Character.isDigit(passwordText.charAt(i))){
                    containsDigit = true;
                };
                if (Character.isLetter(passwordText.charAt(i))){
                    containsLetter = true;
                };
            }

            if (!containsDigit) {
                text = text + "Paswoord moet cijfer bevatten";
            }
            if (!containsLetter){
                text = text + "Paswoord moet letter bevatten";
            }

            if (text == "") {
                text = "Registratie gelukt";
                password.setText("");
                username.setText("");
            }

            // Settings for Toast
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            // Create and show Toast
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
    };
}
