package virus.endtheboss.Modele.Capacites.Boss;

import java.util.List;

import virus.endtheboss.Modele.Personnages.ActionPersonnage;
import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Serveur.Serveur;

/**
 * Created by Valentin on 18/02/2016.
 * Capacite Frappe a distance pour le boss
 */
public class FrappeDistance extends Capacite{
    private Boss sonBoss;

    public FrappeDistance(Boss unBoss, Carte uneCarte){
        super(uneCarte, "Eclair Nova", new FormeEnLosange(6), new FormeEnLosange(2));
        this.sonBoss=unBoss;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        List<Personnage> cibles = laCarte.getPersonnagesDansForme(sonImpact,uneCible);
        for(Personnage p : cibles){
            //Log.i("Pluie Fleche", "Touché enemmi : " + p.getSonNom());
            int degat = 10+sonBoss.getSesDegatDeBase();
            p.coupPersonnage(degat);
        }
    }
}
