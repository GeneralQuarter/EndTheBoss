package virus.endtheboss.Modele.Capacites;

import android.util.Log;

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
public class TirArcTest extends Capacite {

    private Archer sonArcher;

    public TirArcTest(Archer unArcher, Carte uneCarte){
        super(uneCarte, "Tir au boss", new FormeEnLosange(6), new FormeCase());
        this.sonArcher=unArcher;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        Personnage cible = null;
        if(uneCible instanceof Personnage){
            cible = (Personnage) uneCible;
        }
        Random r = new Random();
        int tentative = r.nextInt(100) + 1;
        Log.i("Tir arc", "Tentative : " + tentative + " (Chance Contact " + sonArcher.getChanceContact() + ") " + cible);
        if(tentative < sonArcher.getChanceContact() && cible != null){
            Log.i("Tir Arc", "Touché");
            cible.coupPersonnage(sonArcher.getSesDegatDeBase());

            if(sonArcher.getChanceContact()<100){
                sonArcher.setChanceContact(sonArcher.getChanceContact()+3);
            }else{
                sonArcher.setChanceContact(100);
            }

            Log.i("Tir Arc", "Chance Contact : " + sonArcher.getChanceContact());

            if(sonArcher.getSesDegatDeBase()<40){
                sonArcher.setSesDegatDeBase(sonArcher.getSesDegatDeBase()+2);
            }

            Log.i("Tir Arc", "Degat Arc : " + sonArcher.getSesDegatDeBase());
        }
    }
}
