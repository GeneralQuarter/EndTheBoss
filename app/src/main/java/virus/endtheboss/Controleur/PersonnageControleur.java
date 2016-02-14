package virus.endtheboss.Controleur;

import android.content.Context;
import android.util.Log;

import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Capacites.EtatCapacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Formes.Forme;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Vue.FormeVue;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.PersonnageVue;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public abstract class PersonnageControleur {

    protected Carte c;

    protected Personnage p;
    protected PersonnageVue pv;

    private Context mContext;

    private GameSurface gs;

    private FormeVue forme;

    public PersonnageControleur(Context mContext, GameSurface gs, Carte c){
        this.mContext = mContext;
        this.c = c;
        this.gs = gs;
        setPersonnage();
        this.pv = new PersonnageVue(mContext, p);
        this.gs.layers.add(this.pv);
    }

    public void deplacementPersonnage(Deplacement d){
        switch(d){
            case GAUCHE: if(c.deplacerPersonnage(p, -1, 0)) {
                p.setSaVitesse(p.getSaVitesse()-1);
                pv.updateAnimation(p.getLeft());
                updatePortee();
            } break;
            case DROITE: if(c.deplacerPersonnage(p, 1, 0)) {
                p.setSaVitesse(p.getSaVitesse()-1);
                pv.updateAnimation(p.getRight());
                updatePortee();
            } break;
            case HAUT: if(c.deplacerPersonnage(p, 0, -1)) {
                p.setSaVitesse(p.getSaVitesse()-1);
                pv.updateAnimation(p.getUp());
                updatePortee();
            } break;
            case BAS: if(c.deplacerPersonnage(p, 0, 1)) {
                p.setSaVitesse(p.getSaVitesse()-1);
                pv.updateAnimation(p.getDown());
                updatePortee();
            } break;
            default: break;
        }
    }

    public void clickOnCapaciteButton(int numeroCapacite){
        Capacite c = p.getCapacite(numeroCapacite);
        switch(c.getEtat()){
            case PEUX_LANCER_CAPACITE:
                c.setEtat(EtatCapacite.MONTRE_PORTE_CAPACITE);
                resetCapacites();
                forme = new FormeVue(p.getCapacite(numeroCapacite).getSaPortee(), p);
                gs.layers.add(forme);
                break;
            default:
                break;
        }
    }

    public void resetCapacites(){
        if(forme != null) {
            if (gs.layers.contains(forme)) {
                gs.layers.remove(forme);
                forme = null;
            }
        }
    }

    private void updatePortee(){
        if(forme != null){
            forme.setOrigine(p);
        }
    }

    public PersonnageVue getPersonnageVue() {
        return pv;
    }

    public Personnage getPersonnage() {
        return p;
    }

    protected abstract void setPersonnage();
}
