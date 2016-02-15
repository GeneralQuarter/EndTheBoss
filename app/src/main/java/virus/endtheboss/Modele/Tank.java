package virus.endtheboss.Modele;

import java.util.Random;

/**
 * Created by Valentin on 11/02/2016.
 * Classe de tank, jouable.
 */
public class Tank extends Personnage{
    public Tank(){
        super("Tank");
        Random r = new Random();
        this.saVitaliteMaximale = 100 + r.nextInt(20) + 1;
        this.saVitaliteCourante = this.saVitaliteMaximale;
        this.sonInitiative = 60 + r.nextInt(20) + 1;
        this.saResistance = r.nextInt(3) + 1;
        this.saVitesse = 3;
        this.sesDegatDeBase = 5 + r.nextInt(10) + 1;
    }

    public void coupPersonnage(int value){
        if(saVitaliteCourante - (value - saResistance) > 0){
            saVitaliteCourante = value - saResistance;
        }else{
            saVitaliteCourante = 0;
        }

        if(saResistance < 20){
            saResistance++;
        }
    }


}
