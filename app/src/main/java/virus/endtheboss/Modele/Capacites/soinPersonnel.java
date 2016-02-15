package virus.endtheboss.Modele.Capacites;

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
public class soinPersonnel extends Capacite {
    private Tank sonTank;

    public soinPersonnel(Tank unTank, Carte uneCarte){
        super(uneCarte, "De la vie !" ,new FormeCase(), new FormeCase());
        this.sonTank = unTank;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }

        if (cible != null)
            cible.soignerPersonnage(this.sonTank.getSaResistance()+5);
    }
}

