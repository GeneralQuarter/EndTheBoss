package virus.endtheboss.Modele.Capacites.Pretre;

import java.util.Random;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosangeAvecOrigine;
import virus.endtheboss.Modele.Personnages.Personnage;

/**
 * Created by Valentin on 16/02/2016.
 * Capacite Renfort Degat pour pretre
 */
public class RenfortDegat extends Capacite {

    public RenfortDegat(Carte uneCarte){
        super(uneCarte, "Auxilium", new FormeEnLosangeAvecOrigine(5), new FormeCase());
    }
    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;

            Random rand = new Random();
            int renfort = rand.nextInt(5)+1;
            cible.setSesDegatDeBase(cible.getSesDegatDeBase()+renfort);
            cible.coupPersonnageSansArmure(renfort*2); // Pass armor
        }
    }
}
