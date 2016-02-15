package virus.endtheboss;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virus.endtheboss.Controleur.ArcherControleur;
import virus.endtheboss.Controleur.BossControleur;
import virus.endtheboss.Controleur.PersonnageControleur;
import virus.endtheboss.Controleur.TankControleur;
import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.HealthBar;

public class GameActivity extends FragmentActivity{

    FragmentManager fm;
    GameViewFragment gvf;
    GameControlsFragment gcf;

    GameSurface gs;

    HealthBar hb;

    Button.OnClickListener deplacementListener;
    RelativeLayout.OnClickListener capaciteListener;

    Button left;
    Button right;
    Button up;
    Button down;

    Button attaque;
    Button finTour;

    RelativeLayout layoutCapacite1;
    RelativeLayout layoutCapacite2;
    RelativeLayout layoutCapacite3;
    RelativeLayout layoutCapacite4;

    List<Drawable> layers;

    Carte c;
    PersonnageControleur pc;
    PersonnageControleur bc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        fm = getSupportFragmentManager();
        gvf = (GameViewFragment) fm.findFragmentById(R.id.game_view_fragment);
        gcf = (GameControlsFragment) fm.findFragmentById(R.id.game_controls_fragment);

        gs = (GameSurface) gvf.getView();

        c = new Carte();
        pc = new TankControleur(this, gs, c);
        bc = new BossControleur(this, gs, c);

        hb = (HealthBar) gcf.getView().findViewById(R.id.health_bar);
        hb.setPersonnage(pc.getPersonnage());
        hb.update();

        capaciteListener = new RelativeLayout.OnClickListener() {
            boolean ready = true;
            @Override
            public void onClick(View v) {
                if(ready) {
                    ready = false;
                    if (v == layoutCapacite1) {
                        pc.clickOnCapaciteButton(1);
                    } else if (v == layoutCapacite2) {
                        pc.clickOnCapaciteButton(2);
                    } else if (v == layoutCapacite3) {
                        pc.clickOnCapaciteButton(3);
                    } else if (v == layoutCapacite4) {
                        pc.clickOnCapaciteButton(4);
                    }
                    ready = true;
                }
            }
        };

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

        layoutCapacite1 = (RelativeLayout) gcf.getView().findViewById(R.id.sort_container_1);
        layoutCapacite1.setOnClickListener(capaciteListener);
        layoutCapacite2 = (RelativeLayout) gcf.getView().findViewById(R.id.sort_container_2);
        layoutCapacite2.setOnClickListener(capaciteListener);
        layoutCapacite3 = (RelativeLayout) gcf.getView().findViewById(R.id.sort_container_3);
        layoutCapacite3.setOnClickListener(capaciteListener);
        layoutCapacite4 = (RelativeLayout) gcf.getView().findViewById(R.id.sort_container_4);
        layoutCapacite4.setOnClickListener(capaciteListener);

        TextView nomSort1 = (TextView) layoutCapacite1.findViewWithTag("nomSort");
        nomSort1.setText(pc.getPersonnage().getCapacite(1).getSonNom());

        TextView nomSort2 = (TextView) layoutCapacite2.findViewWithTag("nomSort");
        nomSort2.setText(pc.getPersonnage().getCapacite(2).getSonNom());

        TextView nomSort3 = (TextView) layoutCapacite3.findViewWithTag("nomSort");
        nomSort3.setText(pc.getPersonnage().getCapacite(3).getSonNom());

        TextView nomSort4 = (TextView) layoutCapacite4.findViewWithTag("nomSort");
        nomSort4.setText(pc.getPersonnage().getCapacite(4).getSonNom());

        TextView actionSort1 = (TextView) layoutCapacite1.findViewWithTag("actionSort");
        TextView actionSort2 = (TextView) layoutCapacite2.findViewWithTag("actionSort");
        TextView actionSort3 = (TextView) layoutCapacite3.findViewWithTag("actionSort");
        TextView actionSort4 = (TextView) layoutCapacite4.findViewWithTag("actionSort");

        List<TextView> actionSorts = new ArrayList<>();

        actionSorts.add(actionSort1);
        actionSorts.add(actionSort2);
        actionSorts.add(actionSort3);
        actionSorts.add(actionSort4);

        pc.setActionSorts(actionSorts);

        left = (Button) gcf.getView().findViewById(R.id.button_left);
        left.setOnClickListener(deplacementListener);
        right = (Button) gcf.getView().findViewById(R.id.button_right);
        right.setOnClickListener(deplacementListener);
        up = (Button) gcf.getView().findViewById(R.id.button_up);
        up.setOnClickListener(deplacementListener);
        down = (Button) gcf.getView().findViewById(R.id.button_down);
        down.setOnClickListener(deplacementListener);
        attaque = (Button) gcf.getView().findViewById(R.id.button_attaque);
        finTour = (Button) gcf.getView().findViewById(R.id.button_fin_tour);

        attaque.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                pc.clickOnAttaque();
                hb.update();
            }
        });

        finTour.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                pc.getPersonnage().coupPersonnage(r.nextInt(20) + 10);
                hb.update();
            }
        });


        gs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("GameView", "X : " + (int) (event.getX() / GameValues.tileWidth) + " Y " + (int) (event.getY() / GameValues.tileHeight));
                int x = (int) (event.getX()/GameValues.tileWidth);
                int y = (int) (event.getY() / GameValues.tileHeight);

                return pc.clickOnSurface(new CaseVide(x, y));
            }
        });
    }
}
