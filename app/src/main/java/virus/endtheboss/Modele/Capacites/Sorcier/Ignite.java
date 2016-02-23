package virus.endtheboss.Modele.Capacites.Sorcier;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Effects.Bruler;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Personnages.Personnage;

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
        if(uneCible instanceof Personnage){
            Personnage p = (Personnage) uneCible;
            p.ajouterEffet(new Bruler(3));
        }
    }
}
