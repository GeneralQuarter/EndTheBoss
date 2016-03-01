package virus.endtheboss.Modele.Personnages;

import java.util.Random;

import virus.endtheboss.R;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de sorcier, jouable.
 */
public class Sorcier extends Personnage{
    public Sorcier(){
        super("Sorcier");
        Random r = new Random();
        this.saVitaliteMaximale = 90 + r.nextInt(20) + 1;
        this.saVitaliteCourante = this.saVitaliteMaximale;
        this.sonInitiative = 50 + r.nextInt(20) + 1;
        this.saResistance = 0;
        this.saVitesse = 3;
        this.saVitesseInitiale = 3;
        this.sesDegatDeBase = 5 + r.nextInt(10) + 1;
        this.down = R.raw.sorcier_walk_front;
        this.up = R.raw.sorcier_walk_up;
        this.left = R.raw.sorcier_walk_left;
        this.right = R.raw.sorcier_walk_right;
        this.idle = R.raw.sorcier_front;
    }
}
