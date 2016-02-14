package virus.endtheboss.Modele;

import android.graphics.ColorFilter;

import java.util.Random;

import virus.endtheboss.R;

/**
 * Created by Valentin on 11/02/2016.
 * Classe d'archer, jouable.
 */
public class Archer extends Personnage{
    private int degatArc;
    private int chanceContact;

    public Archer(){
        super("Archer");
        Random r = new Random();
        this.saVitaliteMaximale = 100 + r.nextInt(20) + 1;
        this.saVitaliteCourante = this.saVitaliteMaximale;
        this.sonInitiative = 70 + r.nextInt(20) + 1;
        this.saResistance = 1;
        this.saVitesse = 4;
        this.sesDegatDeBase = 10 + r.nextInt(10) + 1;
        this.idle = R.raw.archer_front;
        this.up = R.raw.archer_walk_up;
        this.left = R.raw.archer_walk_left;
        this.right = R.raw.archer_walk_right;
        this.down = R.raw.archer_walk_front;
        degatArc = 10;
        chanceContact = 50;
    }

    public int getDegatArc() {
        return degatArc;
    }

    public void setDegatArc(int degatArc) {
        this.degatArc = degatArc;
    }

    public int getChanceContact() {
        return chanceContact;
    }

    public void setChanceContact(int chanceContact) {
        this.chanceContact = chanceContact;
    }
}
