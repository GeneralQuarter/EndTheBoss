package virus.endtheboss.Modele.Capacites;

import android.support.annotation.RawRes;

import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.Forme;

/**
 * Created by Valentin on 13/02/2016.
 */
public abstract class Capacite {
    protected @RawRes int sonImage;
    protected Forme saPortee;
    protected Forme sonImpact;
    protected String sonNom;
    protected Carte laCarte;
    protected EtatCapacite etat;

    public Capacite(Carte uneCarte, String unNom, Forme portee, Forme impact){
        this.laCarte = uneCarte;
        this.sonNom = unNom;
        this.etat = EtatCapacite.PEUX_LANCER_CAPACITE;
        this.saPortee = portee;
        this.sonImpact = impact;
    }

    public abstract void lancerSort(CaseCarte uneCible);

    public String getSonNom() {
        return sonNom;
    }

    public EtatCapacite getEtat() {
        return etat;
    }

    public void setEtat(EtatCapacite etat) {
        this.etat = etat;
    }

    public Forme getSaPortee() {
        return saPortee;
    }

    public Forme getSonImpact() {
        return sonImpact;
    }
}
