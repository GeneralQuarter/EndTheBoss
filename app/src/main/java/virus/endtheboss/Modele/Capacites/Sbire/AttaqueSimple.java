package virus.endtheboss.Modele.Capacites.Sbire;


import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Sbire;

/**
 * Created by Valentin on 18/02/2016.
 * Capacite Attaque simple d'un sbire
 */
public class AttaqueSimple extends Capacite{
    private Sbire sonSbire;

    public AttaqueSimple(Sbire unSbire, Carte uneCarte){
        super(uneCarte, "Prend ça !", new FormeEnLosange(3), new FormeCase());
        this.sonSbire=unSbire;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        System.out.println("Prend ça lancé " + uneCible.getClass().toString());
        Personnage cible;
        if (uneCible instanceof Personnage) {
            System.out.println("La cible est un personnage");
            cible = (Personnage) uneCible;
            cible.coupPersonnage(5 + sonSbire.getSesDegatDeBase());
        }
    }
}
