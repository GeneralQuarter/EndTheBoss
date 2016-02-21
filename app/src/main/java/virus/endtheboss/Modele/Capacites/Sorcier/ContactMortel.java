package virus.endtheboss.Modele.Capacites.Sorcier;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 * Capacite Contact Mortel du Sorcier
 */
public class ContactMortel extends Capacite {
    private Sorcier sonSorcier;

    public ContactMortel(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Meurt !", new FormeEnCroix(1), new FormeCase());
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible;
        if (uneCible instanceof Personnage) {
            cible = (Personnage) uneCible;
            cible.coupPersonnage(75);
            sonSorcier.coupPersonnageSansArmure(5); //Pass armor
        }
    }
}
