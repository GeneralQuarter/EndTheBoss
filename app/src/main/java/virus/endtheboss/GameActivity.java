package virus.endtheboss;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import virus.endtheboss.Controleur.PersonnageControleur;
import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Modele.Archer;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.HealthBar;
import virus.endtheboss.Vue.PersonnageVue;

public class GameActivity extends FragmentActivity{

    FragmentManager fm;
    GameViewFragment gvf;
    GameControlsFragment gcf;

    GameSurface gs;

    HealthBar hb;

    Button.OnClickListener deplacementListener;
    Button.OnClickListener changementVieListener;

    Button left;
    Button right;
    Button up;
    Button down;

    Button attaque;
    Button finTour;

    List<Drawable> layers;

    Carte c;
    PersonnageControleur pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        c = new Carte();
        pc = new PersonnageControleur(this, c, new Archer());

        fm = getSupportFragmentManager();
        gvf = (GameViewFragment) fm.findFragmentById(R.id.game_view_fragment);
        gcf = (GameControlsFragment) fm.findFragmentById(R.id.game_controls_fragment);

        gs = (GameSurface) gvf.getView();
        gs.layers.add(pc.getPersonnageVue());

        hb = (HealthBar) gcf.getView().findViewById(R.id.health_bar);
        hb.setPersonnage(pc.getPersonnage());
        hb.update();

        deplacementListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == left){
                    pc.deplacementPersonnage(Deplacement.GAUCHE);
                }else if(v == up){
                    pc.deplacementPersonnage(Deplacement.HAUT);
                }else if(v == down){
                    pc.deplacementPersonnage(Deplacement.BAS);
                }else if(v == right){
                    pc.deplacementPersonnage(Deplacement.DROITE);
                }
                gs.postInvalidate();
            }
        };

        changementVieListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == attaque){
                    pc.coupPersonnage(1);
                }else{
                    pc.soignerPersonnage(1);
                }
                hb.update();
            }
        };

        left = (Button) gcf.getView().findViewById(R.id.button_left);
        left.setOnClickListener(deplacementListener);
        right = (Button) gcf.getView().findViewById(R.id.button_right);
        right.setOnClickListener(deplacementListener);
        up = (Button) gcf.getView().findViewById(R.id.button_up);
        up.setOnClickListener(deplacementListener);
        down = (Button) gcf.getView().findViewById(R.id.button_down);
        down.setOnClickListener(deplacementListener);
        attaque = (Button) gcf.getView().findViewById(R.id.button_attaque);
        attaque.setOnClickListener(changementVieListener);
        finTour = (Button) gcf.getView().findViewById(R.id.button_fin_tour);
        finTour.setOnClickListener(changementVieListener);
    }
}
