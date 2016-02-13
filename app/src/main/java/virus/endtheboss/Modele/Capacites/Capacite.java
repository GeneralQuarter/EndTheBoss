package virus.endtheboss.Modele.Capacites;

import android.support.annotation.RawRes;

import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.Forme;

/**
 * Created by Valentin on 13/02/2016.
 */
public abstract class Capacite {
    private @RawRes int sonImage;
    private Forme saPortee;
    private Forme sonImpact;
    private String sonNom;

    public Capacite(){

    }

    public abstract void lancerSort(CaseCarte uneCible);

}
