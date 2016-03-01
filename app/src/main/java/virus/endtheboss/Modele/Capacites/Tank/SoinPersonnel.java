package virus.endtheboss.Modele.Capacites.Tank;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Tank;

/**
 * Created by Valentin on 14/02/2016.
 * Capacite Soin Personnel du Tank
 */
public class SoinPersonnel extends Capacite {
    private Tank sonTank;

    public SoinPersonnel(Tank unTank, Carte uneCarte){
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
            cible.soignerPersonnage(this.sonTank.getSaResistance()+8);
    }
}

