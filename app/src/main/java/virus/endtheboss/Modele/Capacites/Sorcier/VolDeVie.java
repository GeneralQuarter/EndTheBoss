package virus.endtheboss.Modele.Capacites.Sorcier;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Sorcier;

/**
 * Created by Valentin on 16/02/2016
 * Capacite Vol de Vie du Sorcier
 */
public class VolDeVie extends Capacite {
    private Sorcier sonSorcier;

    public VolDeVie(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Vampiros", new FormeEnCroix(5), new FormeCase());
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible;
        if (uneCible instanceof Personnage) {
            cible = (Personnage) uneCible;
            cible.coupPersonnage(5 + sonSorcier.getSesDegatDeBase());
            sonSorcier.soignerPersonnage(5+(sonSorcier.getSesDegatDeBase()/2));
        }
    }
}
