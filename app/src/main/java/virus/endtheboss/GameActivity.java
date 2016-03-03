package virus.endtheboss;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Client.GestionClient;
import virus.endtheboss.Client.Joueur;
import virus.endtheboss.Controleur.ArcherControleur;
import virus.endtheboss.Controleur.BossControleur;
import virus.endtheboss.Controleur.PersonnageControleur;
import virus.endtheboss.Controleur.PretreControleur;
import virus.endtheboss.Controleur.SbireControleur;
import virus.endtheboss.Controleur.SorcierControleur;
import virus.endtheboss.Controleur.TankControleur;
import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Effects.Effet;
import virus.endtheboss.Modele.Personnages.ActionPersonnage;
import virus.endtheboss.Modele.Personnages.Archer;
import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Sbire;
import virus.endtheboss.Modele.Personnages.Sorcier;
import virus.endtheboss.Modele.Personnages.Support;
import virus.endtheboss.Modele.Personnages.Tank;
import virus.endtheboss.Serveur.MessageServeur;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.HealthBar;

public class GameActivity extends FragmentActivity implements ClientActivity{
    private GameSurface gs;

    private View controlsView;

    private HealthBar hb;

    private AlertDialog alertDialog;
    private Toast toast;

    private Button left;
    private Button right;
    private Button up;
    private Button down;

    private TextView mouvementRestant;

    private RelativeLayout layoutCapacite1;
    private RelativeLayout layoutCapacite2;
    private RelativeLayout layoutCapacite3;
    private RelativeLayout layoutCapacite4;

    private PersonnageControleur pc;

    private List<PersonnageControleur> entites;

    private Carte c;

    private Joueur joueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        entites = new ArrayList<>();

        GestionClient.changeActivity(this);

        joueur = (Joueur) getIntent().getSerializableExtra(LobbyActivity.EXTRA_JOUEUR);

        FragmentManager fm = getSupportFragmentManager();
        GameViewFragment gvf = (GameViewFragment) fm.findFragmentById(R.id.game_view_fragment);
        GameControlsFragment gcf = (GameControlsFragment) fm.findFragmentById(R.id.game_controls_fragment);

        gs = (GameSurface) gvf.getView();

        if(gcf.getView() != null)
            controlsView = gcf.getView();

        hb = (HealthBar) controlsView.findViewById(R.id.health_bar);

        mouvementRestant = (TextView) controlsView.findViewById(R.id.text_view_mouvement_restant);


        RelativeLayout.OnClickListener capaciteListener = new RelativeLayout.OnClickListener() {
            boolean ready = true;
            @Override
            public void onClick(View v) {
                if(ready && pc.isEnTour() && !pc.aLanceSort()) {
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

        Button.OnClickListener deplacementListener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pc.isEnTour()  && pc.getPersonnage().getSaVitesse() > 0) {
                    if (v == left) {
                        pc.deplacementPersonnage(Deplacement.GAUCHE, true);
                    } else if (v == up) {
                        pc.deplacementPersonnage(Deplacement.HAUT, true);
                    } else if (v == down) {
                        pc.deplacementPersonnage(Deplacement.BAS, true);
                    } else if (v == right) {
                        pc.deplacementPersonnage(Deplacement.DROITE, true);
                    }
                    gs.postInvalidate();
                    updateControls();
                }
            }
        };

        layoutCapacite1 = (RelativeLayout) controlsView.findViewById(R.id.sort_container_1);
        layoutCapacite1.setOnClickListener(capaciteListener);
        layoutCapacite2 = (RelativeLayout) controlsView.findViewById(R.id.sort_container_2);
        layoutCapacite2.setOnClickListener(capaciteListener);
        layoutCapacite3 = (RelativeLayout) controlsView.findViewById(R.id.sort_container_3);
        layoutCapacite3.setOnClickListener(capaciteListener);
        layoutCapacite4 = (RelativeLayout) controlsView.findViewById(R.id.sort_container_4);
        layoutCapacite4.setOnClickListener(capaciteListener);

        left = (Button) controlsView.findViewById(R.id.button_left);
        left.setOnClickListener(deplacementListener);
        right = (Button) controlsView.findViewById(R.id.button_right);
        right.setOnClickListener(deplacementListener);
        up = (Button) controlsView.findViewById(R.id.button_up);
        up.setOnClickListener(deplacementListener);
        down = (Button) controlsView.findViewById(R.id.button_down);
        down.setOnClickListener(deplacementListener);
        Button attaque = (Button) controlsView.findViewById(R.id.button_attaque);
        Button finTour = (Button) controlsView.findViewById(R.id.button_fin_tour);

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
                if(pc.isEnTour()) {
                    Log.i("GameActivity", pc.getPersonnage() + "");
                    GestionClient.send(new ActionPersonnage(pc.getPersonnage().getId(), ActionPersonnage.Action.FIN_TOUR, null));
                    pc.setEnTour(false);
                    pc.desactiverCapacites();
                    updateControls();
                }
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
    }

    public void removePersonnageEntite(int id){
        for(PersonnageControleur pc : entites){
            if(pc.getPersonnage().getId() == id){
                gs.removePersonnageVue(id);
                c.emptyCase(pc.getPersonnage());
                entites.remove(pc);
                break;
            }
        }
    }

    private void updateControls(){
        hb.update();
        mouvementRestant.setText(String.format(getString(R.string.defaut_mouvement_restant_text), pc.getPersonnage().getSaVitesse()));
    }

    public void showAlert(String title, String message, String buttonMessage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton(buttonMessage, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
    }

    public void showToast(String message){
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void initControls(){

        ((TextView) controlsView.findViewById(R.id.text_view_nom_personnage)).setText(pc.getPersonnage().getSonNom());

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

        hb.setPersonnage(pc.getPersonnage());

        updateControls();

    }

    private synchronized PersonnageControleur getEntite(int id){
        for(PersonnageControleur pc : entites){
            if(pc.getPersonnage().getId() == id){
                return pc;
            }
        }
        return null;
    }

    @Override
    public void receptionObjectFromClient(Object o) {
        Log.i("GameActivity", "Object reçu : " + o);
        if(o instanceof Carte){
            c = (Carte) o;
        }

        if(o instanceof Personnage){
            Personnage p = (Personnage) o;
            if(joueur.getId() == p.getId()){
                TextView textViewNomClasse = (TextView) controlsView.findViewById(R.id.text_view_nom_classe);
                if(p instanceof Tank){
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[0]);
                    pc = new TankControleur(this, gs, c, (Tank) p);
                }else if(p instanceof Archer){
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[1]);
                    pc = new ArcherControleur(this, gs, c, (Archer) p);
                }else if(p instanceof Sorcier){
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[2]);
                    pc = new SorcierControleur(this, gs, c, (Sorcier) p);
                }else if(p instanceof Support){
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[3]);
                    pc = new PretreControleur(this, gs, c, (Support) p);
                }else if(p instanceof Boss){
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[4]);
                    pc = new BossControleur(this, gs, c, (Boss) p);
                }else if(p instanceof Sbire){
                    textViewNomClasse.setText(getResources().getTextArray(R.array.personnage_array)[5]);
                    pc = new SbireControleur(this, gs, c, (Sbire) p);
                }
                c.placePlayer(p, p.getX(), p.getY());
                initControls();
            }else{
                if(p instanceof Tank){
                    entites.add(new TankControleur(this, gs, c, (Tank) p));
                }else if(p instanceof Archer){
                    entites.add(new ArcherControleur(this, gs, c, (Archer) p));
                }else if(p instanceof Sorcier){
                    entites.add(new SorcierControleur(this, gs, c, (Sorcier) p));
                }else if(p instanceof Support){
                    entites.add(new PretreControleur(this, gs, c, (Support) p));
                }else if(p instanceof Boss){
                    entites.add(new BossControleur(this, gs, c, (Boss) p));
                }else if(p instanceof Sbire){
                    entites.add(new SbireControleur(this, gs, c, (Sbire) p));
                }
                c.placePlayer(p, p.getX(), p.getY());
            }
        }

        if(o instanceof MessageServeur){
            MessageServeur ms = (MessageServeur) o;
            switch(ms.getTypeMessage()){
                case DECONNEXION:
                    removePersonnageEntite(ms.getJoueur().getId());
                    break;
                case BOSS_GAGNE:
                    showAlert("PERDU !!", "Vous êtes des boulets !", "Ok :(");
                    //Return to lobby ?
                    break;
            }
        }

        if(o instanceof ActionPersonnage){
            ActionPersonnage ac = (ActionPersonnage) o;
            switch(ac.getAction()){
                case DEPLACEMENT:
                    Deplacement d = (Deplacement) ac.getValue();
                    PersonnageControleur personnageControleur = getEntite(ac.getPersonnageID());
                    switch(d){
                        case DROITE:
                            if(personnageControleur != null)
                                personnageControleur.deplacementPersonnage(Deplacement.DROITE, false);
                            break;
                        case GAUCHE:
                            if(personnageControleur != null)
                                personnageControleur.deplacementPersonnage(Deplacement.GAUCHE, false);
                            break;
                        case HAUT:
                            if(personnageControleur != null)
                                personnageControleur.deplacementPersonnage(Deplacement.HAUT, false);
                            break;
                        case BAS:
                            if(personnageControleur != null)
                                personnageControleur.deplacementPersonnage(Deplacement.BAS, false);
                            break;
                    }
                    break;
                case DEBUT_TOUR:
                    Log.i("GameActivity", pc.getPersonnage() + "");
                    if(!pc.isMort()) {
                        pc.setEnTour(true);
                        pc.setALanceSort(false);
                        pc.activerCapacites();
                        pc.getPersonnage().appliquerEffets();
                        if (pc.getPersonnage().getSaVitesse() < pc.getPersonnage().getSaVitesseInitiale())
                            pc.getPersonnage().resetVitesse();
                        showAlert("A votre tour !", "C'est à vous de jouer !", "Compris !");
                        updateControls();
                    }else{
                        GestionClient.send(new ActionPersonnage(pc.getPersonnage().getId(), ActionPersonnage.Action.FIN_TOUR, null));
                    }
                    break;
                case TRANSPORT:
                    CaseVide cv = (CaseVide) ac.getValue();
                    if(cv != null) {
                        if (ac.getPersonnageID() == pc.getPersonnage().getId()) {
                            pc.transporterPersonnage(cv);
                        } else {
                            PersonnageControleur pcTrans = getEntite(ac.getPersonnageID());
                            if (pcTrans != null)
                                pcTrans.transporterPersonnage(cv);
                        }
                    }
                    break;
                case DEGAT_AVEC_ARMURE:
                    int value = (Integer) ac.getValue();
                    if(ac.getPersonnageID() == pc.getPersonnage().getId()){
                        pc.getPersonnage().coupPersonnage(value, false);
                        hb.update();
                    }else{
                        PersonnageControleur pcDeg = getEntite(ac.getPersonnageID());
                        if(pcDeg != null)
                            pcDeg.getPersonnage().coupPersonnage(value, false);
                    }
                    break;
                case DEGAT_SANS_ARMURE:
                    value = (Integer) ac.getValue();
                    if(ac.getPersonnageID() == pc.getPersonnage().getId()){
                        pc.getPersonnage().coupPersonnageSansArmure(value, false);
                        hb.update();
                    }else{
                        PersonnageControleur pcDeg = getEntite(ac.getPersonnageID());
                        if(pcDeg != null)
                            pcDeg.getPersonnage().coupPersonnageSansArmure(value, false);
                    }
                    break;
                case SOIN:
                    value = (Integer) ac.getValue();
                    if(ac.getPersonnageID() == pc.getPersonnage().getId()){
                        pc.getPersonnage().soignerPersonnage(value, false);
                        hb.update();
                    }else{
                        PersonnageControleur pcDeg = getEntite(ac.getPersonnageID());
                        if(pcDeg != null)
                            pcDeg.getPersonnage().soignerPersonnage(value, false);
                    }
                    break;
                case EFFET:
                    Effet effet = (Effet) ac.getValue();
                    if(ac.getPersonnageID() == pc.getPersonnage().getId()){
                        pc.getPersonnage().ajouterEffet(effet, false);
                    }else{
                        PersonnageControleur pcEff = getEntite(ac.getPersonnageID());
                        if(pcEff != null)
                            pcEff.getPersonnage().ajouterEffet(effet, false);
                    }
                    break;
                case CHANGE_DEGAT:
                    value = (Integer) ac.getValue();
                    if(ac.getPersonnageID() == pc.getPersonnage().getId()){
                        pc.getPersonnage().setSesDegatDeBase(value, false);
                    }else{
                        PersonnageControleur pcDeg = getEntite(ac.getPersonnageID());
                        if(pcDeg != null)
                            pcDeg.getPersonnage().setSesDegatDeBase(value, false);
                    }
                    break;
                case CHANGE_VITESSE:
                    value = (Integer) ac.getValue();
                    if(ac.getPersonnageID() == pc.getPersonnage().getId()){
                        pc.getPersonnage().setSaVitesse(value, false);
                    }else{
                        PersonnageControleur pcDeg = getEntite(ac.getPersonnageID());
                        if(pcDeg != null)
                            pcDeg.getPersonnage().setSaVitesse(value, false);
                    }
                    break;
                case CHANGE_RESISTANCE:
                    value = (Integer) ac.getValue();
                    if(ac.getPersonnageID() == pc.getPersonnage().getId()){
                        pc.getPersonnage().setSaResistance(value, false);
                    }else{
                        PersonnageControleur pcDeg = getEntite(ac.getPersonnageID());
                        if(pcDeg != null)
                            pcDeg.getPersonnage().setSaResistance(value, false);
                    }
                    break;
                case MORT:
                    if(ac.getPersonnageID() == pc.getPersonnage().getId()){
                        pc.setMort(true);
                        showAlert("HA ! T'ES MORT !", "Tu n'as aucun talent...", "C'est pas gentil :(");
                        pc.getPersonnage().setSaVitaliteCourante(0);
                        gs.removePersonnageVue(pc.getPersonnage().getId());
                        c.emptyCase(pc.getPersonnage());
                        updateControls();
                    }else{
                        PersonnageControleur pcDeg = getEntite(ac.getPersonnageID());
                        if(pcDeg != null) {
                            showToast(pcDeg.getPersonnage().getSonNom() + " est mort !");
                            removePersonnageEntite(pcDeg.getPersonnage().getId());
                        }
                    }
                    break;
                case LANCE_CAPACITE:
                    Capacite capacite = (Capacite) ac.getValue();
                    PersonnageControleur pcDeg = getEntite(ac.getPersonnageID());
                    if(capacite != null && pcDeg != null){
                        showToast(pcDeg.getPersonnage().getSonNom() + " lance " + capacite.getSonNom());
                    }
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {}
}
