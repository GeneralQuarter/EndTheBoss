package virus.endtheboss.Modele.Capacites;

import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 */
public class TransfomationMineur extends Capacite {
    private Sorcier sonSorcier;

    public TransfomationMineur(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Petit démon", new FormeCase(), new FormeCase());
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        sonSorcier.coupPersonnage(50+sonSorcier.getSaResistance());
        sonSorcier.setSesDegatDeBase(sonSorcier.getSesDegatDeBase()+25);
        sonSorcier.setSaResistance(sonSorcier.getSaResistance()+8);
        sonSorcier.setSaVitesse(sonSorcier.getSaVitesse()-1);
    }
}
