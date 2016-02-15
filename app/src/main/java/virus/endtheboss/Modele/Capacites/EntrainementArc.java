package virus.endtheboss.Modele.Capacites;

import android.util.Log;

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
        super(uneCarte, "Devenir bon", new FormeCase(), new FormeCase());
        this.sonArcher=unArcher;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {

        if (sonArcher.getChanceContact() < 100) {
            sonArcher.setChanceContact(sonArcher.getChanceContact() + 6);
        } else {
            sonArcher.setChanceContact(100);
        }

        Log.i("Entrainement Arc", "Chance Contact : " + sonArcher.getChanceContact());


        if (sonArcher.getSesDegatDeBase() < 30) {
            sonArcher.setSesDegatDeBase(sonArcher.getSesDegatDeBase() + 5);
        }

        Log.i("Entrainement Arc", "Degat Arc : " + sonArcher.getSesDegatDeBase());
    }
}
