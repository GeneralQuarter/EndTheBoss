package virus.endtheboss.Modele.Capacites.Tank;

import android.util.Log;

import java.util.Random;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Tank;

/**
 * Created by Valentin on 14/02/2016.
 *
 */
public class CoupEpee extends Capacite {
    private Tank sonTank;

    public CoupEpee(Tank unTank, Carte uneCarte){
        super(uneCarte, "Sprotch" ,new FormeEnCroix(2), new FormeCase());
        this.sonTank = unTank;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }
        Random r = new Random();
        int degatsSupplementaires = r.nextInt(10) + 1;

        if (cible != null) {
            Log.i("Coup Epee", "Dégats émis : " + (sonTank.getSesDegatDeBase() + degatsSupplementaires + sonTank.getSaResistance()));
            Log.i("Coup Epee", "Dégats base : " + sonTank.getSesDegatDeBase());
            Log.i("Coup Epee", "Dégats random : " + degatsSupplementaires);
            Log.i("Coup Epee", "Dégats résistance : " + sonTank.getSaResistance());
            cible.coupPersonnage(sonTank.getSesDegatDeBase() + degatsSupplementaires + sonTank.getSaResistance());
        }
    }
}
