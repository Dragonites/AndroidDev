package com.example.rosta.elections2016;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Rosta on 12-11-2016.
 */
public class SettingsActivity extends Activity {

    private Spinner spinner;
    private SeekBar seekBar;
    private TextView seekBarValue;
    private CheckBox checkBox;

    // Settings voor Seekbar
    int step = 4;
    int max = 2100;
    int min = 1800;

    // Bestandsnaam van de Shared Preferences
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        spinner = (Spinner) this.findViewById(R.id.spinner);
        seekBar = (SeekBar) this.findViewById(R.id.seekbar);
        seekBarValue = (TextView) this.findViewById(R.id.seekbar_value);
        checkBox = (CheckBox) this.findViewById(R.id.checkbox);

        /*
         * Aangezien we dit niet in XML kunnen, moeten we het hier instellen.
         * We willen mogelijkheden van 1800 tot 2100, met stappen van 4 jaar.
         * Dit geeft 75 mogelijke waarden voor de seekbar.
         * De range zal dus gaan van 0 tot (2100-1800)/4 = 75.
         */
        seekBar.setMax( (max - min) / step );
        // Stel een onSeekBarChangeListener in
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    // Wat er moet gebeuren wanneer de seekbar verandert
    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener()
    {
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            /*
            * Waarden opvragen van de seekbar.
            * Als progress op 13 uitkomt, dan is de waarde 1800 + (13 * 4) = 1852
            */
            int value = min + (progress * step);

            // Zet deze waarde in het veld onder de seekbar
            seekBarValue.setText(value + ""); // professionele convert naar String
        }
    };

    // Runt wanneer de activity uit zicht verdwijnt, hier slaan we de settings op
    @Override
    protected void onPause() {
        super.onPause();

        // Vraag SharedPreferences-bestand op en maak een editor zodat je dit kan bewerken.
        // PREFS_NAME is een constante, bovenaan gedifinieerd.
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        // We slaan de positie van het geselecteerde item op (int)
        editor.putInt("spinner", spinner.getSelectedItemPosition());
        // We slaan de progressie van de seekbar op (int)
        editor.putInt("seekbar", seekBar.getProgress());
        // We slaan de state van de checkbox op (boolean)
        editor.putBoolean("checkbox", checkBox.isChecked());

        // Opslaan van de settings
        editor.apply();
    }

    // Runt wanneer de activity terug zichtbaar wordt, hier laden we de settings
    @Override
    protected void onResume() {
        super.onResume();

        // Laden van SharedPreferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        // Setten van geselecteerde item, 0 = default value
        spinner.setSelection(settings.getInt("spinner", 0));
        // Setten van seekbarprogressie
        seekBar.setProgress(settings.getInt("seekbar", 0));
        // Setten van checkbox
        checkBox.setChecked(settings.getBoolean("checkbox", false));
    }
}
