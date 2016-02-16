package virus.endtheboss.Modele.Capacites;

import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 */
public class ContactMortel extends Capacite {
    private Sorcier sonSorcier;

    public ContactMortel(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Meurt !", new FormeEnCroix(1), new FormeCase());
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if (uneCible instanceof Personnage) {
            cible = (Personnage) uneCible;
        }
        cible.coupPersonnage(75);
        sonSorcier.coupPersonnage(5+sonSorcier.getSaResistance()); //Pass armor
    }
}
