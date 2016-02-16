package virus.endtheboss.Modele.Capacites;

import java.util.Random;

import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Support;

/**
 * Created by Valentin on 16/02/2016.
 */
public class RenfortDegat extends Capacite{
    private Support sonSupport;
    private int renfort;

    public RenfortDegat(Support unSupport, Carte uneCarte){
        super(uneCarte, "Auxilium", new FormeEnLosange(5), new FormeCase());
        this.sonSupport=unSupport;
    }
    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }
        Random rand = new Random();
        renfort = rand.nextInt(5)+1;
        cible.setSesDegatDeBase(cible.getSesDegatDeBase()+renfort);
        cible.coupPersonnage(renfort*2+cible.getSaResistance()); // Pass armor
    }
}
