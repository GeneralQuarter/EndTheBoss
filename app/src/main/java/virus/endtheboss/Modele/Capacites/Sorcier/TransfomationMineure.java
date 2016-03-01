package virus.endtheboss.Modele.Capacites.Sorcier;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Personnages.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 * Capacite Transformation mineure du Sorcier
 */
public class TransfomationMineure extends Capacite {
    private Sorcier sonSorcier;

    public TransfomationMineure(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Petit démon", new FormeCase(), new FormeCase());
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        sonSorcier.coupPersonnage(40+sonSorcier.getSaResistance());
        sonSorcier.setSesDegatDeBase(sonSorcier.getSesDegatDeBase()+35);
        sonSorcier.setSaResistance(sonSorcier.getSaResistance()+8);
        sonSorcier.setSaVitesse(sonSorcier.getSaVitesse()-1);
    }
}
