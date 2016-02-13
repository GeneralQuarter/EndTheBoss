package virus.endtheboss.Modele.Formes;

import virus.endtheboss.Modele.CaseCarte;

/**
 * Created by Valentin on 13/02/2016.
 */
public abstract class Forme {
    CaseCarte saCaseInitiale;
    int saTaille;

    public Forme(){

    }

    public abstract void afficherForme(CaseCarte origine);
}
