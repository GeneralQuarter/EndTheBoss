package virus.endtheboss.Modele;

import java.util.Random;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de sorcier, jouable.
 */
public class Sorcier extends Personnage{
    public Sorcier(int unePositionX, int unePositionY){
        super();
        Random r = new Random();
        this.saVitalite = 80 + r.nextInt(20) + 1;
        this.sonInitiative = 50 + r.nextInt(20) + 1;
        this.saResistance = 0;
        this.saVitesse = 3;
        this.sesDegatDeBase = 5 + r.nextInt(5) + 1;
        this.saPositionX = unePositionX;
        this.saPositionY = unePositionY;
    }
}
