package virus.endtheboss.Modele.Capacites.Sorcier;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;

/**
 * Created by Valentin on 16/02/2016.
 * Capacite ignite du sorcier
 */
public class Ignite extends Capacite {

    public Ignite(Carte uneCarte){
        super(uneCarte, "Brûle !", new FormeEnCroix(5), new FormeCase());
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        //TODO Coder le sort Ignite
    }
}
