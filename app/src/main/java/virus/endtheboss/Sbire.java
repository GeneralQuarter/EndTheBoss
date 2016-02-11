package virus.endtheboss;

import java.util.Random;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de sbire, non jouable.
 */
public class Sbire extends Personnage{
    public Sbire(){
        super();
        Random r = new Random();
        this.saVitalite = 50;
        this.sonInitiative = 70;
        this.saResistance = 0;
        this.saVitesse = 5;
        this.sesDegatDeBase = 5;
    }
}
