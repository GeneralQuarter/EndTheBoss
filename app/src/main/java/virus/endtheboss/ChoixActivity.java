package virus.endtheboss;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import virus.endtheboss.Client.GestionClient;
import virus.endtheboss.Client.Joueur;
import virus.endtheboss.Serveur.MessageServeur;

/**
 * Created by Quentin Gangler on 16/02/2016.
 * Activité permettant de choisir l'adresse ip et le nom de son personnage
 */
public class ChoixActivity extends Activity implements ClientActivity{

    public final static String EXTRA_JOUEUR = "virus.endtheboss.JOUEUR";

    private ProgressDialog progressDialog;
    private AlertDialog errorDialog;

    private Joueur joueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix);

        (findViewById(R.id.button_connexion)).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adresse = ((EditText) findViewById(R.id.edit_text_adresse_ip)).getText().toString();
                String nomPersonnage = ((EditText) findViewById(R.id.edit_text_nom_personnage)).getText().toString();
                joueur = new Joueur(nomPersonnage);
                GestionClient.connect(joueur, adresse, ChoixActivity.this);
                progressDialog = ProgressDialog.show(ChoixActivity.this, null, "Connexion en cours...", true);

            }
        });
        TextView texteView = (TextView) findViewById(R.id.titre);
        Typeface font = Typeface.createFromAsset(getAssets(), "font/darktime.ttf");
        texteView.setTypeface(font);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void switchToLobby(){
        progressDialog.dismiss();
        Intent intent = new Intent(this, LobbyActivity.class);
        intent.putExtra(EXTRA_JOUEUR, joueur);
        startActivity(intent);
    }

    public void showError(String title, String message){
        if(progressDialog != null)
            progressDialog.dismiss();
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

    @Override
    public void receptionObjectFromClient(Object o) {
        if(o instanceof MessageServeur){
            MessageServeur ms = (MessageServeur) o;
            switch(ms.getTypeMessage()){
                case CONNEXION:
                    if(joueur.getId() == -1 && ms.getJoueur().getNom().equals(joueur.getNom())){
                        joueur.setId(ms.getJoueur().getId());
                        switchToLobby();
                    }
                    break;
                case ERR_NOM_CLIENT_INVALIDE:
                    if(joueur.getId() == -1 && ms.getJoueur().getNom().equals(joueur.getNom())){
                        showError("Connection impossible !", "Nom déjà pris !");
                    }
                    break;
                case ERR_PARTIE_EN_COURS:
                    if(joueur.getId() == -1 && ms.getJoueur().getNom().equals(joueur.getNom())){
                        showError("Connection impossible !", "Partie en cours");
                    }
                    break;
                case ERR_SERVEUR_PLEIN:
                    if(joueur.getId() == -1 && ms.getJoueur().getNom().equals(joueur.getNom())){
                        showError("Connection impossible !", "Serveur plein");
                    }
                    break;
            }
        }

        if(o instanceof String){
            showError("Problème de connexion", (String) o);
        }
    }
}
