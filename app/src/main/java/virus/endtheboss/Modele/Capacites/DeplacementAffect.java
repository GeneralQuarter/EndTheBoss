package virus.endtheboss.Modele.Capacites;

import android.util.Log;

import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Formes.FormeEnLosangeAvecOrigine;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Support;

/**
 * Created by Valentin on 16/02/2016.
 */
public class DeplacementAffect extends  Capacite{
    private Support sonSupport;
    private boolean accelere;

    public DeplacementAffect(Support unSupport, Carte uneCarte){
        super(uneCarte, "Acceretardatus", new FormeEnLosangeAvecOrigine(5), new FormeCase());
        this.sonSupport=unSupport;
        this.accelere = true;
    }
    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }
        if (accelere){
            cible.setSaVitesse(cible.getSaVitesse()+1);
            accelere = false;
            Log.i("Acceretardus", "Vitesse : " + sonSupport.getSaVitesse());
        }else{
            cible.setSaVitesse(cible.getSaVitesse()-1);
            accelere = true;
            Log.i("Acceretardus", "Vitesse : "  + sonSupport.getSaVitesse());
        }
    }
}
