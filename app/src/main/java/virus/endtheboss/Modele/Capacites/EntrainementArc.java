package virus.endtheboss.Modele.Capacites;

import java.util.Random;

import virus.endtheboss.Modele.Archer;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnCroix;
import virus.endtheboss.Modele.Personnage;

/**
 * Created by Valentin on 13/02/2016.
 */
public class EntrainementArc extends Capacite {
    private Archer sonArcher;

    public EntrainementArc(Archer unArcher, Carte uneCarte){
        super(uneCarte);
        this.sonArcher=unArcher;
        this.saPortee=new FormeCase();
        this.sonImpact = new FormeCase();
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        CaseCarte cible;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }

        if (sonArcher.getChanceContact() < 100) {
            sonArcher.setChanceContact(sonArcher.getChanceContact() + 6);
        } else {
            sonArcher.setChanceContact(100);
        }

        if (sonArcher.getDegatArc() < 30) {
            sonArcher.setDegatArc(sonArcher.getDegatArc() + 5);
        }
    }
}
