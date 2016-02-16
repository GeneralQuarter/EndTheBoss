package virus.endtheboss.Modele.Capacites;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Formes.FormeEnLosangeAvecOrigine;
import virus.endtheboss.Modele.Formes.FormeTous;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 */
public class Meteore extends Capacite {
    private Sorcier sonSorcier;
    private CaseCarte caseCible;

    public Meteore(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Météor", new FormeTous(), new FormeEnLosange(3));
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        List<Personnage> cibles = new ArrayList<>();
        cibles = laCarte.getPersonnagesDansForme(sonImpact,uneCible);
        for(Personnage p : cibles){
            //Log.i("Pluie Fleche", "Touché enemmi : " + p.getSonNom());
            p.coupPersonnage(5+sonSorcier.getSesDegatDeBase());
        }
    }
}
