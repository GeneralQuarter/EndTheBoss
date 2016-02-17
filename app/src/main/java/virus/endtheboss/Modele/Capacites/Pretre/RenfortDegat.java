package virus.endtheboss.Modele.Capacites.Pretre;

import java.util.Random;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Formes.FormeEnLosangeAvecOrigine;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Support;

/**
 * Created by Valentin on 16/02/2016.
 */
public class RenfortDegat extends Capacite {
    private Support sonSupport;

    public RenfortDegat(Support unSupport, Carte uneCarte){
        super(uneCarte, "Auxilium", new FormeEnLosangeAvecOrigine(5), new FormeCase());
        this.sonSupport=unSupport;
    }
    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;

            Random rand = new Random();
            int renfort = rand.nextInt(5)+1;
            cible.setSesDegatDeBase(cible.getSesDegatDeBase()+renfort);
            cible.coupPersonnageSansArmure(renfort*2); // Pass armor
        }
    }
}
