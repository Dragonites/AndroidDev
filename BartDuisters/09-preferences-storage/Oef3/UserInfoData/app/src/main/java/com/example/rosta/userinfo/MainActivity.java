package com.example.rosta.userinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String splitchar = ";";

    EditText name;
    EditText address;
    EditText phone;
    EditText email;
    CheckBox newsletter;

    String[] result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.editTextName);
        address = (EditText) findViewById(R.id.editTextAddress);
        phone = (EditText) findViewById(R.id.editTextPhone);
        email = (EditText) findViewById(R.id.editTextEmail);
        newsletter = (CheckBox) findViewById(R.id.checkboxNewsletter);

        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFile(String content) throws IOException {
        // Alle code die nodig is om een string naar een bestand te schrijven
        FileOutputStream fos = openFileOutput("userinfo.txt", MODE_PRIVATE);
        fos.write(content.getBytes());
        fos.close();
    }

    public void readFile() throws IOException {

        // Alle code die nodig is om een reeks van bytes uit te lezen
        FileInputStream fis = openFileInput("userinfo.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuilder b = new StringBuilder();
        while (bis.available() != 0) {
            char c = (char) bis.read();
            b.append(c);
        }
        bis.close();
        fis.close();

        // De bytes omzetten naar een string en deze splitten op het gegeven slitkarakter
        result = b.toString().split(splitchar);

        // De gegevens invullen op basis van wat opgeslagen is
        name.setText(result[0]);
        address.setText(result[1]);
        phone.setText(result[2]);
        email.setText(result[3]);
        if (result[4].equals("true")) {
            newsletter.setChecked(true);
        } else {
            newsletter.setChecked(false);
        }
    }


    public void saveClicked(View view) {
        String content = name.getText().toString() + splitchar
                + address.getText().toString() + splitchar
                + phone.getText().toString() + splitchar
                + email.getText().toString() + splitchar
                + newsletter.isChecked();
        try {
            createFile(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Opgeslagen in: " + getFilesDir().toString(), Toast.LENGTH_LONG).show();
    }

    public void cancelClicked(View view) {
        Toast.makeText(this, "Nee, klik maar op de Back Button als je weg wilt gaan!", Toast.LENGTH_SHORT).show();
    }
}
