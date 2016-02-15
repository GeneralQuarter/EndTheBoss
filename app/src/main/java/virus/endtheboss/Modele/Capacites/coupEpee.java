package virus.endtheboss.Modele.Capacites;

import android.util.Log;

import java.util.Random;

import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Tank;

/**
 * Created by Valentin on 14/02/2016.
 */
public class coupEpee extends Capacite {
    private Tank sonTank;

    public coupEpee(Tank unTank, Carte uneCarte){
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

        if (cible != null)
            cible.coupPersonnage(this.sonTank.getSesDegatDeBase()+ degatsSupplementaires + this.sonTank.getSaResistance());
    }
}
