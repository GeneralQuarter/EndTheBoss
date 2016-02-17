package virus.endtheboss.Modele.Capacites.Tank;

import android.util.Log;

import java.util.Random;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Tank;

/**
 * Created by Valentin on 14/02/2016.
 * Cette fonction devra être terminée une fois qu'on appliquera les "Etats" aux personnages.
 */
public class Provocation extends Capacite {
    private Tank sonTank;

    public Provocation(Tank unTank, Carte uneCarte){
        super(uneCarte, "Je te #&@!#" ,new FormeEnLosange(4), new FormeCase());
        this.sonTank = unTank;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }

        if (cible != null && cible.getSaResistance() > 0){
            cible.setSaResistance(cible.getSaResistance()-2);
            Log.i("Provocation", "Resistance cible : " + cible.getSaResistance());
        }

    }
}
