package virus.endtheboss.Modele.Capacites.Tank;

import android.util.Log;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnages.Personnage;

/**
 * Created by Valentin on 14/02/2016.
 * Cette fonction devra être terminée une fois qu'on appliquera les "Etats" aux personnages.
 */
public class Provocation extends Capacite {

    public Provocation(Carte uneCarte){
        super(uneCarte, "Je te #&@!#" ,new FormeEnLosange(4), new FormeCase());
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }

        if (cible != null && cible.getSaResistance() > 0){
            cible.setSaResistance(cible.getSaResistance()-3);
            Log.i("Provocation", "Resistance cible : " + cible.getSaResistance());
        }

    }
}
