package virus.endtheboss.Modele.Capacites.Tank;


import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Tank;

/**
 * Created by Valentin on 14/02/2016.
 * Capacite Grappin du Tank
 */
public class Grappin extends Capacite {
    private Tank sonTank;


    public Grappin(Tank unTank, Carte uneCarte){
        super(uneCarte, "Viens par là !" ,new FormeEnLosange(4), new FormeCase());
        this.sonTank = unTank;
        this.setSaPortee(new FormeEnLosange(sonTank.getSaResistance()));

    }

    @Override
    public void lancerSort(CaseCarte uneCible) {



        Personnage cible = null;

        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }
        CaseCarte caseDessous = laCarte.get(new CaseVide(sonTank.getX(),sonTank.getY()+1));

        if (cible != null){
            if(caseDessous instanceof CaseVide){
                laCarte.transporterPersonnage(cible,sonTank.getX(),sonTank.getY()+1);
            }
        }
    }
}
