package virus.endtheboss;

import java.util.Random;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de boss, non jouable.
 */
public class Boss extends Personnage {
    public Boss(){
        super();
        Random r = new Random();
        this.saVitalite = 300 + r.nextInt(50) + 1;
        this.sonInitiative = 90;
        this.saResistance = 3;
        this.saVitesse = 3;
        this.sesDegatDeBase = 10 + r.nextInt(5) + 1;
    }
}
