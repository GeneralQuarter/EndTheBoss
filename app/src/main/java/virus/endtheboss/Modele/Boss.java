package virus.endtheboss.Modele;

import java.util.Random;

import virus.endtheboss.R;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de boss, non jouable.
 */
public class Boss extends Personnage {
    public Boss(){
        super("Boss");
        Random r = new Random();
        this.saVitaliteMaximale = 300 + r.nextInt(50) + 1;
        this.saVitaliteCourante = this.saVitaliteMaximale;
        this.sonInitiative = 90;
        this.saResistance = 3;
        this.saVitesse = 3;
        this.sesDegatDeBase = 10 + r.nextInt(5) + 1;
        this.down = R.raw.boss_walk_front;
        this.up = R.raw.boss_walk_up;
        this.left = R.raw.boss_walk_left;
        this.right = R.raw.boss_walk_right;
        this.idle = R.raw.boss_front;
    }
}
