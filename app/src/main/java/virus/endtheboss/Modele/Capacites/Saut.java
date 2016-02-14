package virus.endtheboss.Modele.Capacites;

import virus.endtheboss.Modele.Archer;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;

/**
 * Created by Valentin on 13/02/2016.
 */
public class Saut extends Capacite {

    private Archer sonArcher;

    public Saut(Archer unArcher, Carte uneCarte){
        super(uneCarte, "Saut", new FormeEnCroix(3), new FormeCase());
        this.sonArcher=unArcher;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        if(uneCible instanceof CaseVide){
            laCarte.transporterPersonnage(sonArcher,uneCible.getX(),uneCible.getY());
        }
    }
}
