package virus.endtheboss.Modele.Capacites.Sorcier;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Formes.FormeEnLosangeAvecOrigine;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Sorcier;

/**
 * Created by Valentin on 16/02/2016.
 */
public class Tornade extends Capacite {
    private Sorcier sonSorcier;
    private CaseCarte caseCible;
    private Random rand;

    public Tornade(Sorcier unSorcier, Carte uneCarte){
        super(uneCarte, "Tornado !", new FormeEnLosangeAvecOrigine(7), new FormeEnLosange(4));
        this.sonSorcier=unSorcier;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        List<Personnage> cibles = new ArrayList<>();
        cibles = laCarte.getPersonnagesDansForme(sonImpact,uneCible);
        for(Personnage p : cibles){
            Random rand = new Random();
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);
            while(!laCarte.isCaseVide(new CaseVide(x, y))){
                x = rand.nextInt(20);
                y = rand.nextInt(20);
            }
            laCarte.transporterPersonnage(p, x, y);
            //Log.i("Pluie Fleche", "Touché enemmi : " + p.getSonNom());
            p.coupPersonnage(sonSorcier.getSesDegatDeBase() - 3);
        }
    }
}