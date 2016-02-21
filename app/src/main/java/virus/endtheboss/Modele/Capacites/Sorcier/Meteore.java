package virus.endtheboss.Modele.Capacites.Sorcier;

import java.util.List;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeEnLosangeAvecOrigine;
import virus.endtheboss.Modele.Formes.FormeTous;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 * Capacite Météore du Sorcier
 */
public class Meteore extends Capacite {
    private Sorcier sonSorcier;

    public Meteore(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Météor", new FormeTous(), new FormeEnLosangeAvecOrigine(3));
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        List<Personnage> cibles = laCarte.getPersonnagesDansForme(sonImpact,uneCible);
        for(Personnage p : cibles){
            //Log.i("Pluie Fleche", "Touché enemmi : " + p.getSonNom());
            p.coupPersonnage(5+sonSorcier.getSesDegatDeBase());
        }
    }
}
