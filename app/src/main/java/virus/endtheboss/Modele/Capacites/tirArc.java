package virus.endtheboss.Modele.Capacites;

import virus.endtheboss.Modele.Archer;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Personnage;

/**
 * Created by Valentin on 13/02/2016.
 */
public class tirArc extends Capacite {

    private Archer sonArcher;

    public tirArc(Archer unArcher){
        super();
        this.sonArcher=unArcher;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {

    }
}
