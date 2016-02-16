package virus.endtheboss.Modele.Capacites;

import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Formes.FormeTous;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 */
public class TeleportationUrgence extends Capacite{
    private Sorcier sonSorcier;

    public TeleportationUrgence(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "A+", new FormeTous(), new FormeCase());
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        if(uneCible instanceof CaseVide){
            laCarte.transporterPersonnage(sonSorcier,uneCible.getX(),uneCible.getY());
            sonSorcier.coupPersonnage(10 + sonSorcier.getSaResistance()); //Pass Armor
            sonSorcier.setSesDegatDeBase(0);
        }
    }
}
