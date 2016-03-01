package virus.endtheboss.Modele.Personnages;

import java.util.Random;

import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.R;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de tank, jouable.
 */
public class Tank extends Personnage{
    public Tank(){
        super("Tank");
        Random r = new Random();
        this.saVitaliteMaximale = 180 + r.nextInt(30) + 1;
        this.saVitaliteCourante = this.saVitaliteMaximale;
        this.sonInitiative = 60 + r.nextInt(20) + 1;
        this.saResistance = 3;
        this.saVitesse = 3;
        this.saVitesseInitiale = 3;
        this.sesDegatDeBase = r.nextInt(10) + 1;
        this.idle = R.raw.tank_front;
        this.up = R.raw.tank_walk_up;
        this.down = R.raw.tank_walk_front;
        this.left = R.raw.tank_walk_left;
        this.right = R.raw.tank_walk_right;

    }
}
