package virus.endtheboss.Modele.Capacites.Boss;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Modele.Boss;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnage;

/**
 * Created by Valentin on 18/02/2016.
 */
public class FrappeDistance extends Capacite{
    private Boss sonBoss;

    public FrappeDistance(Boss unBoss, Carte uneCarte){
        super(uneCarte, "Eclair Nova", new FormeEnLosange(6), new FormeEnLosange(2));
        this.sonBoss=unBoss;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        List<Personnage> cibles = new ArrayList<>();
        cibles = laCarte.getPersonnagesDansForme(sonImpact,uneCible);
        for(Personnage p : cibles){
            //Log.i("Pluie Fleche", "Touché enemmi : " + p.getSonNom());
            p.coupPersonnage(10+sonBoss.getSesDegatDeBase());
        }
    }
}
