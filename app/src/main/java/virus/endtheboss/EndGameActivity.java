package virus.endtheboss;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Client.Joueur;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Score;

/**
 * Created by Quentin Gangler on 03/03/2016.
 * Activité récapitulative des scores
 */

public class EndGameActivity extends Activity{

    private Personnage[] personnages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        personnages = (Personnage[]) getIntent().getSerializableExtra(GameActivity.EXTRA_ENTITES);

        LinearLayout joueursLayout = (LinearLayout) findViewById(R.id.joueurs_list_layout);
        LinearLayout iasLayout = (LinearLayout) findViewById(R.id.ias_list_layout);
        LinearLayout dernierePartiesLayout = (LinearLayout) findViewById(R.id.dernieres_partie_list_layout);

        TextView textViewTempsJeu = (TextView) findViewById(R.id.temps_de_jeu_value);
        String tempsJeu = getIntent().getStringExtra(GameActivity.EXTRA_TEMPS_JEU);
        textViewTempsJeu.setText(tempsJeu);

        Button backToMenu = (Button) findViewById(R.id.button_retourner_menu);
        backToMenu.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String winner = "";
        Joueur joueur = (Joueur) getIntent().getSerializableExtra(GameActivity.EXTRA_JOUEUR);

        for(Personnage p: personnages){
            TextView personnageTextView = new TextView(this);
            if(p.getSaVitaliteCourante() != 0) {
                personnageTextView.setText(p.getSonNom() + " : " + p.getSaVitaliteCourante() + " pv");
                if(p.getId() <= 666)
                    winner = p.getSonNom();
            }else
                personnageTextView.setText(p.getSonNom() + " : mort");

            if(p.getId() < 666){
                joueursLayout.addView(personnageTextView);
            }else{
                iasLayout.addView(personnageTextView);
            }
        }

        ScoreHelper scoreHelper = new ScoreHelper(this);

        List<Score> scores = scoreHelper.getScores();

        for(Score score : scores){
            TextView scoreTextView = new TextView(this);
            scoreTextView.setText(score.getNomGagnant() + " à gagné en " + score.getTempsJeu());
            if(score.isWinner()){
                scoreTextView.setTextColor(Color.GREEN);
            }else{
                scoreTextView.setTextColor(Color.RED);
            }
            dernierePartiesLayout.addView(scoreTextView);
        }

        scoreHelper.addScore(new Score(winner, winner.equals(joueur.getNom()), tempsJeu));

        TextView textViewResulatJeu = (TextView) findViewById(R.id.text_victory_label);
        if(winner.equals(joueur.getNom())){
            textViewResulatJeu.setText("Vous avez remporté la partie !");
        }else if(winner.equals("Boss")){
            textViewResulatJeu.setText(getString(R.string.ia_gagne_label));
        }else{
            textViewResulatJeu.setText(String.format(getString(R.string.remporte_partie_label), winner));
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChoixActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
