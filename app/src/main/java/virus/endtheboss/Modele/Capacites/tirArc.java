package virus.endtheboss.Modele.Capacites;

import java.util.Random;

import virus.endtheboss.Modele.Archer;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Personnage;

/**
 * Cette capacité est le tir à l'arc de l'archer.
 * Created by Valentin on 13/02/2016.
 */
public class tirArc extends Capacite {

    private Archer sonArcher;

    public tirArc(Archer unArcher, Carte uneCarte){
        super(uneCarte, "Tir Arc");
        this.sonArcher=unArcher;
        this.saPortee = new FormeEnLosange(6);
        this.sonImpact = new FormeCase();
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }
        Random r = new Random();
        int tentative = r.nextInt(100) + 1;
        if(tentative < sonArcher.getChanceContact() && cible != null){
            cible.coupPersonnage(sonArcher.getDegatArc());

            if(sonArcher.getChanceContact()<100){
                sonArcher.setChanceContact(sonArcher.getChanceContact()+3);
            }else{
                sonArcher.setChanceContact(100);
            }

            if(sonArcher.getDegatArc()<30){
                sonArcher.setDegatArc(sonArcher.getDegatArc()+2);
            }
        }
    }
}
