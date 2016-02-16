package virus.endtheboss.Modele.Capacites;

import java.util.Random;

import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 */
public class ChaineSombre extends Capacite{
    private Sorcier sonSorcier;
    private int aleatoire;

    public ChaineSombre(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Tir au boss", new FormeEnLosange(5), new FormeCase());
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }
        Random rand = new Random();
        aleatoire=rand.nextInt(100)+1;
        if(aleatoire<=60){
            if (aleatoire<=45){
                cible.coupPersonnage(3 +cible.getSaResistance()); //Pass Armor
            }else{
                cible.soignerPersonnage(1);
            }
            cible.setSesDegatDeBase(cible.getSesDegatDeBase()-1);
            this.lancerSort(uneCible);
        }else{
            if (aleatoire<=65){
                cible.coupPersonnage(10 + cible.getSaResistance()); //Pass Armor
                cible.setSaResistance(cible.getSaResistance() - 1);
            }else if(aleatoire <= 70){
                cible.soignerPersonnage(5);
            }else{
                cible.coupPersonnage(2 + cible.getSaResistance()); //Pass Armor
            }
        }
    }
}
