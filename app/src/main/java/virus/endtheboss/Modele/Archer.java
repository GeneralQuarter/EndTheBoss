package virus.endtheboss.Modele;

import java.util.Random;

/**
 * Created by Valentin on 11/02/2016.
 * Classe d'archer, jouable.
 */
public class Archer extends Personnage{
    public Archer(int unePositionX, int unePositionY){
        super();
        Random r = new Random();
        this.saVitalite = 100 + r.nextInt(20) + 1;
        this.sonInitiative = 70 + r.nextInt(20) + 1;
        this.saResistance = 1;
        this.saVitesse = 4;
        this.sesDegatDeBase = 10 + r.nextInt(10) + 1;
        this.saPositionX = unePositionX;
        this.saPositionY = unePositionY;
    }
}
