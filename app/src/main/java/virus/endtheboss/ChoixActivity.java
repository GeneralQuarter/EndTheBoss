package virus.endtheboss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Quentin Gangler on 16/02/2016.
 */
public class ChoixActivity extends Activity {

    public final static String EXTRA_ADRESSE = "virus.endtheboss.ADRESSE";
    public final static String EXTRA_NOM_PERSO = "virus.endtheboss.NOM_PERSO";
    public final static String EXTRA_CHOIX_PERSO = "virus.endtheboss.CHOIX_PERSO";

    private int choixPerso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);
        choixPerso = -1;

        ((Button) findViewById(R.id.button_connexion)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame();
            }
        });

        Spinner.OnItemSelectedListener itemSelectedListener = new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choixPerso = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                choixPerso = -1;
            }
        };

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.personnage_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(itemSelectedListener);
    }

    private void switchToGame(){
        String adresse = ((EditText) findViewById(R.id.edit_text_adresse_ip)).getText().toString();
        String nomPersonnage = ((EditText) findViewById(R.id.edit_text_nom_personnage)).getText().toString();
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_ADRESSE, adresse);
        intent.putExtra(EXTRA_NOM_PERSO, nomPersonnage);
        intent.putExtra(EXTRA_CHOIX_PERSO, choixPerso);
        startActivity(intent);
    }
}
