package virus.endtheboss.Controleur;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Capacites.EtatCapacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
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

    protected Context mContext;

    protected GameSurface gs;

    private List<TextView> actionSort;

    private FormeVue forme;
    private CaseCarte cible;

    public PersonnageControleur(Context mContext, GameSurface gs, Carte c){
        this.mContext = mContext;
        this.c = c;
        this.gs = gs;
        setPersonnage();
        setVuePersonnage();
    }

    public void deplacementPersonnage(Deplacement d){
        switch(d){
            case GAUCHE:
                if(!isEnChoixImpact()){
                    if(c.deplacerPersonnage(p, -1, 0)) {
                        pv.updateAnimation(p.getLeft());
                        updateDeplacement();
                    }
            } break;
            case DROITE:
                if(!isEnChoixImpact()){
                    if(c.deplacerPersonnage(p, 1, 0)) {
                        pv.updateAnimation(p.getRight());
                        updateDeplacement();
                    }
                } break;
            case HAUT:
                if(!isEnChoixImpact()){
                    if(c.deplacerPersonnage(p, 0, -1)) {
                        updateDeplacement();
                        pv.updateAnimation(p.getUp());
                    }
                } break;
            case BAS:
                if(!isEnChoixImpact()) {
                    if (c.deplacerPersonnage(p, 0, 1)) {
                        updateDeplacement();
                        pv.updateAnimation(p.getDown());
                    }
                }break;
            default: break;
        }
    }

    private boolean isEnChoixImpact(){
        return p.getCapaciteEncours() != -1 && p.getCapacite(p.getCapaciteEncours()).getEtat() == EtatCapacite.MONTRE_IMPACT_CAPACITE;
    }

    private void updateDeplacement(){
        updatePortee();
        p.setSaVitesse(p.getSaVitesse() - 1);
    }

    public void clickOnCapaciteButton(int numeroCapacite){
        Capacite c = p.getCapacite(numeroCapacite);
        switch(c.getEtat()){
            case PEUX_LANCER_CAPACITE:
                c.setEtat(EtatCapacite.MONTRE_PORTE_CAPACITE);
                actionSort.get(numeroCapacite-1).setText("Choisir impact");
                changerForme(p.getCapacite(numeroCapacite).getSaPortee(), p);
                p.setCapaciteEncours(numeroCapacite);
                setEtatCapaciteSaufCourante(EtatCapacite.PEUX_LANCER_CAPACITE);
                break;
            case MONTRE_IMPACT_CAPACITE:
                c.setEtat(EtatCapacite.MONTRE_PORTE_CAPACITE);
                actionSort.get(numeroCapacite-1).setText("Choisir impact");
                changerForme(p.getCapacite(numeroCapacite).getSaPortee(), p);
                setEtatCapaciteSaufCourante(EtatCapacite.PEUX_LANCER_CAPACITE);
            default:
                break;
        }
    }

    public boolean clickOnSurface(CaseCarte origine){
        if(p.getCapaciteEncours() != -1){
            Capacite c = p.getCapacite(p.getCapaciteEncours());
            switch(c.getEtat()){
                case MONTRE_PORTE_CAPACITE:
                    if(p.getCapacite(p.getCapaciteEncours()).getSaPortee().isDansForme(p, origine)) {
                        c.setEtat(EtatCapacite.MONTRE_IMPACT_CAPACITE);
                        actionSort.get(p.getCapaciteEncours()-1).setText("Annuler impact <<");
                        changerForme(p.getCapacite(p.getCapaciteEncours()).getSonImpact(), origine);
                        cible = origine;
                        setEtatCapaciteSaufCourante(EtatCapacite.NE_PEUX_PAS_LANCER_CAPACITE);
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public void clickOnAttaque(){
        if(p.getCapaciteEncours() != -1 && cible != null){
            Capacite c = p.getCapacite(p.getCapaciteEncours());
            switch(c.getEtat()){
                case MONTRE_IMPACT_CAPACITE:
                    c.lancerSort(this.c.get(cible));
                    resetLayerForme();
                    p.setCapaciteEncours(-1);
                    setEtatCapaciteSaufCourante(EtatCapacite.PEUX_LANCER_CAPACITE);
                    cible = null;
                    break;
                default:break;
            }
        }
    }

    public void resetLayerForme(){
        if(forme != null) {
            if (gs.layers.contains(forme)) {
                gs.layers.remove(forme);
                forme = null;
            }
        }
    }

    private void setEtatCapaciteSaufCourante(EtatCapacite etat){
        for(int i = 1; i <= 4; i++){
            if(i != p.getCapaciteEncours()){
                p.getCapacite(i).setEtat(etat);
                switch(etat){
                    case PEUX_LANCER_CAPACITE:
                        actionSort.get(i-1).setText("Lancer sort >>");
                        break;
                    case NE_PEUX_PAS_LANCER_CAPACITE:
                        actionSort.get(i-1).setText("");
                }
            }
        }

    }

    private void updatePortee(){
        if(forme != null){
            forme.setOrigine(p);
        }
    }

    private void changerForme(Forme f, CaseCarte origine){
        resetLayerForme();
        forme = new FormeVue(f, origine);
        gs.layers.add(forme);
    }

    public void setActionSorts(List<TextView> actionSorts){
        this.actionSort = actionSorts;
    }

    public PersonnageVue getPersonnageVue() {
        return pv;
    }

    public Personnage getPersonnage() {
        return p;
    }

    protected abstract void setPersonnage();

    protected abstract void setVuePersonnage();
}
