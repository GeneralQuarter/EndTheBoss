package virus.endtheboss.Modele.Capacites.Sorcier;

import android.util.Log;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 */
public class FireBall extends Capacite {
    private Sorcier sonSorcier;

    public FireBall(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Fire Ball !", new FormeEnCroix(5), new FormeCase());
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if (uneCible instanceof Personnage) {
            cible = (Personnage) uneCible;
            cible.coupPersonnage(25 + sonSorcier.getSesDegatDeBase());
        }
    }
}
