package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Vue.PersonnageVue;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class PersonnageControleur {

    private Carte c;

    private Personnage p;
    private PersonnageVue pv;

    private Context mContext;

    public PersonnageControleur(Context mContext, Carte c, Personnage p){
        this.mContext = mContext;
        this.p = p;
        this.c = c;
        this.pv = new PersonnageVue(mContext, p);

        this.c.placePlayer(p, 2, 1);
    }

    public void deplacementPersonnage(Deplacement d){
        switch(d){
            case GAUCHE: if(c.deplacerPersonnage(p, -1, 0)) {
                p.setSaVitesse(p.getSaVitesse()-1);
                pv.updateAnimation(p.getLeft());
            } break;
            case DROITE: if(c.deplacerPersonnage(p, 1, 0)) {
                p.setSaVitesse(p.getSaVitesse()-1);
                pv.updateAnimation(p.getRight());
            } break;
            case HAUT: if(c.deplacerPersonnage(p, 0, -1)) {
                p.setSaVitesse(p.getSaVitesse()-1);
                pv.updateAnimation(p.getUp());
            } break;
            case BAS: if(c.deplacerPersonnage(p, 0, 1)) {
                p.setSaVitesse(p.getSaVitesse()-1);
                pv.updateAnimation(p.getDown());
            } break;
            default: break;
        }
    }

    public PersonnageVue getPersonnageVue() {
        return pv;
    }

    public Personnage getPersonnage() {
        return p;
    }

    public void coupPersonnage(int value){
        if(p.getSaVitaliteCourante()-value > 0){
            p.setSaVitaliteCourante(p.getSaVitaliteCourante()-value);
        }
    }

    public void soignerPersonnage(int value){
        if(p.getSaVitaliteCourante()+value>p.getSaVitaliteMaximale()){
            p.setSaVitaliteCourante(p.getSaVitaliteMaximale());
        }else{
            p.setSaVitaliteCourante(p.getSaVitaliteCourante()+value);
        }
    }
}
