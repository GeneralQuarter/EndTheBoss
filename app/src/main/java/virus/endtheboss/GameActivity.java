package virus.endtheboss;

import android.content.DialogInterface;
import android.content.Intent;
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
import virus.endtheboss.Controleur.PretreControleur;
import virus.endtheboss.Controleur.SbireControleur;
import virus.endtheboss.Controleur.SorcierControleur;
import virus.endtheboss.Controleur.TankControleur;
import virus.endtheboss.Enumerations.Action;
import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.ActionPersonnage;
import virus.endtheboss.Modele.Archer;
import virus.endtheboss.Modele.Boss;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Sbire;
import virus.endtheboss.Modele.Sorcier;
import virus.endtheboss.Modele.Support;
import virus.endtheboss.Modele.Tank;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.GestionReseau;
import virus.endtheboss.Vue.HealthBar;

public class GameActivity extends FragmentActivity{

    FragmentManager fm;
    GameViewFragment gvf;
    GameControlsFragment gcf;

    Client client;

    PlayerID playerID;

    List<PersonnageControleur> entites;
    int quiJoue;
    int nbJoueur;

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
    Personnage p;
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

        hb = (HealthBar) gcf.getView().findViewById(R.id.health_bar);


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
                if(pc.isEnTour()) {
                    if (v == left) {
                        pc.deplacementPersonnage(Deplacement.GAUCHE);
                    } else if (v == up) {
                        pc.deplacementPersonnage(Deplacement.HAUT);
                    } else if (v == down) {
                        pc.deplacementPersonnage(Deplacement.BAS);
                    } else if (v == right) {
                        pc.deplacementPersonnage(Deplacement.DROITE);
                    }
                    gs.postInvalidate();
                }
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
                if(client != null) {
                    client.send(new ActionPersonnage(Action.FIN_TOUR, playerID));
                    pc.setEnTour(false);
                }
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
                //Log.i("GameView", "X : " + (int) (event.getX() / GameValues.tileWidth) + " Y " + (int) (event.getY() / GameValues.tileHeight));
                int x = (int) (event.getX() / GameValues.tileWidth);
                int y = (int) (event.getY() / GameValues.tileHeight);

                return pc.clickOnSurface(new CaseVide(x, y));
            }
        });

        init();
    }

    public void objectReseauRecu(Object o){
        Log.i("GameActivity", "Object Reçu " + o);
        //Reception code
        if(o instanceof PlayerID){
            playerID = (PlayerID) o;
            Log.i("GameActivity", "Reçu playerID : " + playerID.getId());
            if(playerID.isHote()){
                postInit();
                pc.setEnTour(true);
                quiJoue = 1;
                nbJoueur = 1;
                c.placePlayer(pc.getPersonnage(), 2, 1);
                bc = new BossControleur(this, gs, c, new Boss());
                c.placePlayer(bc.getPersonnage(), 10, 10);
            }else{
                client.send(p);
            }
        }else if(o instanceof Carte){
            boolean found = false;
            if(!playerID.isHote()){
                for(Personnage p : c.updateCarte((Carte) o)){
                    for(int i = 0; i < entites.size() && !found; i++) {
                        if(entites.get(i).getPersonnage().getSonNom().equals(p.getSonNom())){
                            found = true;
                            if (this.p.getSonNom().equals(p.getSonNom())) {
                                Log.i("GameActivity", "Found my character : " + p.getSonNom());
                                this.p = p;
                            }else {
                                Log.i("GameActivity", "Found another character than mine : " + p.getSonNom());
                                entites.get(i).setPersonnage(p);
                            }
                        }
                    }
                    if(!found){
                        Log.i("GameActivity", "Character not found " + p.getSonNom());
                        if (this.p.getSonNom().equals(p.getSonNom())){
                            this.p = p;
                            postInit();
                        }else{
                            entites.add(new PersonnageControleur(this, gs, c, p));
                        }
                    }
                    found = false;
                }
            }
        }else if(o instanceof ActionPersonnage){
            ActionPersonnage ac = (ActionPersonnage) o;
            Log.i("GameActivity", "Reçu Action Personnage " + ac.getAction());
            if(ac.getAction() != Action.DEBUT_TOUR && ac.getAction() != Action.FIN_TOUR) {
                switch (ac.getAction()) {
                    case DEPLACEMENT:
                        Log.i("GameActivity", "entites lenght : " + entites.size());
                        for(int i = 0; i < entites.size(); i++) {
                            Log.i("GameActivity", "entites " + i + " : " + entites.get(i).getPersonnage().getSonNom());
                            if (entites.get(i).getPersonnage().getSonNom().equals(ac.getP().getSonNom())) {
                                entites.get(i).deplacementPersonnage(ac.getD());
                            }
                        }
                        break;
                }
            }else{
                switch(ac.getAction()){
                    case FIN_TOUR:
                        if(playerID.isHote()){
                            for(PersonnageControleur pc : entites){
                                if(!pc.getPersonnage().getSonNom().equals("Boss") && !pc.getPersonnage().getSonNom().equals("Sbire")){
                                    if(quiJoue + 1 <= nbJoueur){
                                        quiJoue++;
                                    }else{
                                        quiJoue = 1;
                                    }
                                    client.send(new ActionPersonnage(Action.DEBUT_TOUR, new PlayerID(quiJoue)));
                                }
                            }
                        } break;
                    case DEBUT_TOUR:
                        Log.i("GameActivity", "Reçu Début tour pour " + ac.getDestinataire().getId());
                        if(playerID.getId() == ac.getDestinataire().getId()){
                            Log.i("GameActivity", "Je démarre mon tour");
                            pc.setEnTour(true);
                        } break;
                }
            }
        }else if(o instanceof Personnage) {
            Personnage p = (Personnage) o;
            if(playerID.isHote()){
                nbJoueur++;
                c.placePlayer(p, 2, 3);
                entites.add(new PersonnageControleur(this, gs, c, p));
                client.send(c);
            }
        }else{
            Log.i("GameActivity","Recu Object inconnu : " + o);
        }
    }

    private void postInit(){
        Log.i("GameActivity", "Post Init lancé pour p " + p.getSonNom());
        if(p instanceof Tank){
            pc = new TankControleur(this, gs, c, (Tank) p);
        }else if(p instanceof Archer){
            pc = new ArcherControleur(this, gs, c, (Archer) p);
        }else if(p instanceof Sorcier){
            pc = new SorcierControleur(this, gs, c, (Sorcier) p);
        }else if(p instanceof Support){
            pc = new PretreControleur(this, gs, c, (Support) p);
        }else if(p instanceof Boss){
            pc = new BossControleur(this, gs, c, (Boss) p);
        }else if(p instanceof Sbire){
            pc = new SbireControleur(this, gs, c, (Sbire) p);
        }

        hb.setPersonnage(pc.getPersonnage());
        hb.update();

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
    }

    private void init(){
        Intent intent = getIntent();
        String adresse = intent.getStringExtra(ChoixActivity.EXTRA_ADRESSE);
        String nomPersonnage = intent.getStringExtra(ChoixActivity.EXTRA_NOM_PERSO);
        int choixPerso = intent.getIntExtra(ChoixActivity.EXTRA_CHOIX_PERSO, -1);

        Log.i("Init", "Choix Perso : " + choixPerso);
        Log.i("Init", "Adresse : " + adresse);
        Log.i("Init", "Nom personnage : " + nomPersonnage);

        if(!adresse.isEmpty()) {
            Log.i("Init", "Test de connection");
            //client = new Client(adresse, this);
            client = new Client("10.1.250.41", this);
            client.execute();
            GestionReseau.client = client;
        }

        entites = new ArrayList<>();

        TextView textViewNomClasse = (TextView) gcf.getView().findViewById(R.id.text_view_nom_classe);
        ((TextView) gcf.getView().findViewById(R.id.text_view_nom_personnage)).setText(nomPersonnage);

        if(!nomPersonnage.isEmpty() && choixPerso != -1){
            switch(choixPerso) {
                case 0:
                    p = new Tank();
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[0]);
                    break;
                case 1:
                    p = new Archer();
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[1]);
                    break;
                case 2:
                    p = new Sorcier();
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[2]);
                    break;
                case 3:
                    p = new Support();
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[3]);
                    break;
                case 4:
                    p = new Boss();
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[4]);
                    break;
                case 5:
                    p = new Sbire();
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[5]);
                    break;
                default:break;
            }
            p.setSonNom(nomPersonnage);

            if(client == null){
                postInit();
                pc.setEnTour(true);
                c.placePlayer(pc.getPersonnage(), 2, 1);
                bc = new BossControleur(this, gs, c, new Boss());
                c.placePlayer(bc.getPersonnage(), 10, 10);
            }

        }
    }
}
