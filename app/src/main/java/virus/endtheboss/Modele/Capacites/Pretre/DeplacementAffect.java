package virus.endtheboss.Modele.Capacites.Pretre;

import android.util.Log;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosangeAvecOrigine;
import virus.endtheboss.Modele.Personnages.Personnage;

/**
 * Created by Valentin on 16/02/2016.
 * Capacite Deplacement Affect pour le pretre
 */
public class DeplacementAffect extends Capacite {
    private boolean accelere;

    public DeplacementAffect(Carte uneCarte){
        super(uneCarte, "Acceretardatus", new FormeEnLosangeAvecOrigine(5), new FormeCase());
        this.accelere = true;
    }
    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible;
        if(uneCible instanceof Personnage) {
            cible = (Personnage) uneCible;
            if (accelere){
                cible.setSaVitesse(cible.getSaVitesse()+1);
                accelere = false;
                Log.i("Acceretardus", "Vitesse de " + cible.getSonNom() + ": " + cible.getSaVitesse());
            }else{
                cible.setSaVitesse(cible.getSaVitesse()-1);
                accelere = true;
                Log.i("Acceretardus", "Vitesse de " + cible.getSonNom() + ": "  + cible.getSaVitesse());
            }
        }
    }
}
