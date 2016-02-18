package virus.endtheboss.Modele;

import java.util.Random;

import virus.endtheboss.R;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de sbire, non jouable.
 */
public class Sbire extends Personnage{
    public Sbire(){
        super("Sbire");
        Random r = new Random();
        this.saVitaliteMaximale = 50;
        this.saVitaliteCourante = this.saVitaliteMaximale;
        this.sonInitiative = 70;
        this.saResistance = 0;
        this.saVitesse = 5;
        this.sesDegatDeBase = 5;
        this.down = R.raw.sbire_walk_front;
        this.up = R.raw.sbire_walk_up;
        this.left = R.raw.sbire_walk_left;
        this.right = R.raw.sbire_walk_right;
        this.idle = R.raw.sbire_front;
    }
}
