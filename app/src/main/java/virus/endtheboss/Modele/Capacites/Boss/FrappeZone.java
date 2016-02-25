package virus.endtheboss.Modele.Capacites.Boss;

import java.util.List;

import virus.endtheboss.Modele.Personnages.ActionPersonnage;
import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Serveur.Serveur;

/**
 * Created by Valentin on 18/02/2016.
 * Capacite Frappe Zone pour le boss
 */
public class FrappeZone extends Capacite {

    private Boss sonBoss;

    public FrappeZone(Boss unBoss, Carte uneCarte){
        super(uneCarte, "Frappe Nova", new FormeCase(), new FormeEnLosange(3));
        this.sonBoss=unBoss;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        List<Personnage> cibles = laCarte.getPersonnagesDansForme(sonImpact,uneCible);
        for(Personnage p : cibles){
            //Log.i("Pluie Fleche", "Touché enemmi : " + p.getSonNom());
            int degat = 15 + sonBoss.getSesDegatDeBase();
            p.coupPersonnage(degat);
        }
    }
}
