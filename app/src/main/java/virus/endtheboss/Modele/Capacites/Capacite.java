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

    public Capacite(Carte uneCarte){
        this.laCarte = uneCarte;
    }

    public abstract void lancerSort(CaseCarte uneCible);

}
