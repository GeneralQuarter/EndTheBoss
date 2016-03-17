package virus.endtheboss;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Client.GestionClient;
import virus.endtheboss.Client.Joueur;
import virus.endtheboss.Serveur.MessageServeur;

/**
 * Created by Quentin Gangler on 19/02/2016.
 * Activité permettant de rassembler tous les joueurs pour une partie
 */
public class LobbyActivity extends Activity implements ClientActivity{

    public final static String EXTRA_JOUEUR = "virus.endtheboss.JOUEUR_ID";

    private Joueur joueur;

    private List<Joueur> joueurs;

    private TextView nom1;
    private TextView nom2;
    private TextView nom3;
    private TextView nom4;

    private Spinner spinner;
    private TextView classe2;
    private TextView classe3;
    private TextView classe4;

    private TextView countJoueurs;

    private Button confirmButton;

    private AlertDialog errorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        joueur = (Joueur) getIntent().getSerializableExtra(ChoixActivity.EXTRA_JOUEUR);
        joueurs = new ArrayList<>();

        GestionClient.changeActivity(this);

        nom1 = (TextView) findViewById(R.id.textViewNom1);
        nom2 = (TextView) findViewById(R.id.textViewNom2);
        nom3 = (TextView) findViewById(R.id.textViewNom3);
        nom4 = (TextView) findViewById(R.id.textViewNom4);

        classe2 = (TextView) findViewById(R.id.textViewPersonnage2);
        classe3 = (TextView) findViewById(R.id.textViewPersonnage3);
        classe4 = (TextView) findViewById(R.id.textViewPersonnage4);

        countJoueurs = (TextView) findViewById(R.id.textViewCountPlayers);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.personnage_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        confirmButton = (Button) findViewById(R.id.buttonReadyLancer);

        confirmButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                joueur.setChoixPerso(spinner.getSelectedItemPosition());
                Log.i("Lobby", "Item selected " + joueur.getChoixPerso());
                GestionClient.send(new MessageServeur(new Joueur(joueur), MessageServeur.TypeMessage.CHOIX_PERSO));
            }
        });

        GestionClient.send(new MessageServeur(joueur, MessageServeur.TypeMessage.DEMANDE_JOUEURS_LIST));

        TextView texteView = (TextView) findViewById(R.id.textViewTitreLobby);
        Typeface font = Typeface.createFromAsset(getAssets(), "font/darkjubile.ttf");
        texteView.setTypeface(font);
    }

    private void updatejoueur(Joueur joueur){
        for(Joueur j : joueurs){
            if(j.getId() == joueur.getId()){
                joueurs.set(joueurs.indexOf(j), joueur);
            }
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public void receptionObjectFromClient(Object o) {
        Log.i("Lobby", "Bien reçu object : " + o);
        if(o instanceof ArrayList){
            joueurs = (ArrayList<Joueur>) o;
            updateListJoueurs();
        }

        if(o instanceof MessageServeur){
            MessageServeur ms = (MessageServeur) o;
            switch(ms.getTypeMessage()){
                case CONNEXION:
                    ajouterJoueur(ms.getJoueur());
                    break;
                case DECONNEXION:
                    enleverJoueur(ms.getJoueur());
                    break;
                case ERR_PERSO_PRIS:
                    joueur.setChoixPerso(-1);
                    showError("Personnage déjà pris", "Désolé votre personnage est déjà pris");
                    break;
                case CHOIX_PERSO:
                    if(joueur.getId() == ms.getJoueur().getId()){
                        spinner.setEnabled(false);
                        confirmButton.setText(getString(R.string.attente_joueur));
                        confirmButton.setEnabled(false);
                    }else{
                        Log.i("Lobby", "update Joueur choix : " + ms.getJoueur().getChoixPerso());
                        updatejoueur(ms.getJoueur());
                        updateListJoueurs();
                    }
                    break;
                case LANCEMENT_PARTIE:
                    Intent intent = new Intent(this, GameActivity.class);
                    intent.putExtra(EXTRA_JOUEUR, joueur);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    break;
            }
        }
    }

    private void ajouterJoueur(Joueur joueur){
        joueurs.add(joueur);
        updateListJoueurs();
    }

    private void enleverJoueur(Joueur joueur){
        for(Joueur j : joueurs){
            if(j.getId() == joueur.getId()){
                joueurs.remove(joueurs.indexOf(j));
                break;
            }
        }
        updateListJoueurs();
    }

    public void showError(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("Compris !", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                errorDialog.dismiss();
            }
        });
        errorDialog = builder.create();
        errorDialog.show();
    }

    private void updateListJoueurs(){
        for(Joueur j : joueurs){
            Log.i("Lobby", j.getNom() + " : " + j.getChoixPerso());
        }
        countJoueurs.setText(String.format(getString(R.string.defaut_player_count_label), (joueurs.size()+1)));
        nom1.setText(joueur.getNom());
        if(joueurs.size() == 0){
            nom2.setText(getString(R.string.vide));
            nom3.setText(getString(R.string.vide));
            nom4.setText(getString(R.string.vide));
            classe2.setText(getString(R.string.none));
            classe3.setText(getString(R.string.none));
            classe4.setText(getString(R.string.none));
        }else if(joueurs.size() == 1){
            nom2.setText(joueurs.get(0).getNom());
            if(joueurs.get(0).getChoixPerso() != -1)
                classe2.setText(getResources().getStringArray(R.array.personnage_array)[joueurs.get(0).getChoixPerso()]);
            else
                classe2.setText(getString(R.string.none));
            nom3.setText(getString(R.string.vide));
            nom4.setText(getString(R.string.vide));
            classe3.setText(getString(R.string.none));
            classe4.setText(getString(R.string.none));
        }else if(joueurs.size() == 2){
            nom2.setText(joueurs.get(0).getNom());
            if(joueurs.get(0).getChoixPerso() != -1)
                classe2.setText(getResources().getStringArray(R.array.personnage_array)[joueurs.get(0).getChoixPerso()]);
            else
                classe2.setText(getString(R.string.none));
            nom3.setText(joueurs.get(1).getNom());
            if(joueurs.get(1).getChoixPerso() != -1)
                classe3.setText(getResources().getStringArray(R.array.personnage_array)[joueurs.get(1).getChoixPerso()]);
            else
                classe3.setText(getString(R.string.none));
            nom4.setText(getString(R.string.vide));
            classe4.setText(getString(R.string.none));
        }else if(joueurs.size() == 3){
            nom2.setText(joueurs.get(0).getNom());
            if(joueurs.get(0).getChoixPerso() != -1)
                classe2.setText(getResources().getStringArray(R.array.personnage_array)[joueurs.get(0).getChoixPerso()]);
            else
                classe2.setText(getString(R.string.none));
            nom3.setText(joueurs.get(1).getNom());
            if(joueurs.get(1).getChoixPerso() != -1)
                classe3.setText(getResources().getStringArray(R.array.personnage_array)[joueurs.get(1).getChoixPerso()]);
            else
                classe3.setText(getString(R.string.none));
            nom4.setText(joueurs.get(2).getNom());
            if(joueurs.get(2).getChoixPerso() != -1)
                classe4.setText(getResources().getStringArray(R.array.personnage_array)[joueurs.get(2).getChoixPerso()]);
            else
                classe4.setText(getString(R.string.none));
        }
    }

    @Override
    public void onBackPressed() {}
}
