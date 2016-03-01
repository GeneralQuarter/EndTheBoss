package virus.endtheboss.Modele.Capacites.Sorcier;

import java.util.Random;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnages.Personnage;

/**
 * Created by Valentin on 16/02/2016.
 * Capacite Chaine Sombre d'un sorcier
 */
public class ChaineSombre extends Capacite {

    public ChaineSombre(Carte uneCarte){
        super(uneCarte, "Chaine sombre", new FormeEnLosange(5), new FormeCase());
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;

            Random rand = new Random();
            int aleatoire=rand.nextInt(100)+1;
            if(aleatoire<=60){
                if (aleatoire<=45){
                    cible.coupPersonnageSansArmure(8); //Pass Armor
                }else{
                    cible.soignerPersonnage(1);
                }
                cible.setSesDegatDeBase(cible.getSesDegatDeBase()-1);
                this.lancerSort(uneCible);
            }else{
                if (aleatoire<=65){
                    cible.coupPersonnageSansArmure(15); //Pass Armor
                    if(cible.getSaResistance()-3>=0)
                        cible.setSaResistance(cible.getSaResistance() - 3);

                }else if(aleatoire <= 70){
                    cible.soignerPersonnage(5);
                }else{
                    cible.coupPersonnageSansArmure(3); //Pass Armor
                }
            }
        }
    }
}
