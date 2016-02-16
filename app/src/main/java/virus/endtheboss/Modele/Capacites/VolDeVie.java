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
public class VolDeVie extends Capacite {
    private Sorcier sonSorcier;

    public VolDeVie(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Tir au boss", new FormeEnCroix(5), new FormeCase());
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if (uneCible instanceof Personnage) {
            cible = (Personnage) uneCible;
        }
        cible.coupPersonnage(10 + sonSorcier.getSesDegatDeBase());
        sonSorcier.soignerPersonnage(5+(sonSorcier.getSesDegatDeBase()/2));
    }
}
