package virus.endtheboss.Modele.Capacites.Pretre;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosangeAvecOrigine;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Support;

/**
 * Created by Valentin on 16/02/2016.
 *
 */
public class SoinCible extends Capacite {

    private Support sonSupport;
    private int nombreUtilisation;
    private int degatSort;

    public SoinCible(Support unSupport, Carte uneCarte){
        super(uneCarte, "Benedictus", new FormeEnLosangeAvecOrigine(5), new FormeCase());
        this.sonSupport=unSupport;
        this.nombreUtilisation = 0;
        this.degatSort = 5;
    }
    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;

            switch(nombreUtilisation){
                case 0 : cible.soignerPersonnage(20 + this.sonSupport.getSesDegatDeBase()); break;
                case 1 : cible.soignerPersonnage(15 + this.sonSupport.getSesDegatDeBase()); break;
                case 2 : cible.soignerPersonnage(10 + this.sonSupport.getSesDegatDeBase()); break;
                case 3 : cible.soignerPersonnage(5 + this.sonSupport.getSesDegatDeBase()); break;
                case 4 : cible.soignerPersonnage(25 + this.sonSupport.getSesDegatDeBase()); break;
                default : cible.coupPersonnage(degatSort + this.sonSupport.getSesDegatDeBase()); degatSort+=5; break;
            }

            nombreUtilisation++;
        }
    }
}
