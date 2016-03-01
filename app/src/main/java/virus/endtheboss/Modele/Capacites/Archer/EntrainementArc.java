package virus.endtheboss.Modele.Capacites.Archer;

import android.util.Log;


import virus.endtheboss.Modele.Personnages.Archer;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;

/**
 * Created by Valentin on 13/02/2016.
 * Capacite Entrainement Arc pour l'archer
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
            sonArcher.setChanceContact(sonArcher.getChanceContact() + 7);
        } else {
            sonArcher.setChanceContact(100);
        }

        Log.i("Entrainement Arc", "Chance Contact : " + sonArcher.getChanceContact());


        if (sonArcher.getSesDegatDeBase() < 50) {
            sonArcher.setSesDegatDeBase(sonArcher.getSesDegatDeBase() + 10);
        }

        Log.i("Entrainement Arc", "Degat Arc : " + sonArcher.getSesDegatDeBase());
    }
}
