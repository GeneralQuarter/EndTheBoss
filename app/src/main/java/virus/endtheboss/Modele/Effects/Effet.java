package virus.endtheboss.Modele.Effects;

import java.io.Serializable;

import virus.endtheboss.Modele.Personnages.Personnage;

/**
 * Created by Quentin Gangler on 23/02/2016.
 * Classe qui représente un effet appliqué à chaque tour pendant un certain nombre de tours
 */
public abstract class Effet implements Serializable{

    private String nom;
    private int dureeTour;

    public Effet(String nom, int dureeTour){
        this.nom = nom;
        this.dureeTour = dureeTour;
    }

    public abstract void appliquerEffet(Personnage p);

    public int getDureeTour() {
        return dureeTour;
    }

    public String getNom() {
        return nom;
    }

    public void setDureeTour(int dureeTour) {
        this.dureeTour = dureeTour;
    }
}
