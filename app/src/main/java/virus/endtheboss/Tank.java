package virus.endtheboss;

import java.util.Random;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de tank, jouable.
 */
public class Tank extends Personnage{
    public Tank(){
        super();
        Random r = new Random();
        this.saVitalite = 100 + r.nextInt(20) + 1;
        this.sonInitiative = 60 + r.nextInt(20) + 1;
        this.saResistance = r.nextInt(3) + 1;
        this.saVitesse = 3;
        this.sesDegatDeBase = 5 + r.nextInt(10) + 1;
    }
}
