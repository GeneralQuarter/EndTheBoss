package virus.endtheboss.Modele.Personnages;

import java.util.Random;

import virus.endtheboss.R;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de support, jouable.
 */
public class Support extends Personnage{
    public Support(){
        super("Support");
        Random r = new Random();
        this.saVitaliteMaximale = 100 + r.nextInt(20) + 1;
        this.saVitaliteCourante = this.saVitaliteMaximale;
        this.sonInitiative = 60 + r.nextInt(20) + 1;
        this.saResistance = r.nextInt(3) + 1;
        this.saVitesse = 3;
        this.saVitesseInitiale = 3;
        this.sesDegatDeBase = r.nextInt(5) + 1;
        this.idle = R.raw.pretre_front;
        this.up = R.raw.pretre_walk_up;
        this.down = R.raw.pretre_walk_front;
        this.left = R.raw.pretre_walk_left;
        this.right = R.raw.pretre_walk_right;

    }
}
