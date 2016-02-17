package virus.endtheboss.Modele.Formes;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Modele.CaseCarte;

/**
 * Created by Valentin on 13/02/2016.
 */
public abstract class Forme {
    int saTaille;

    public Forme(int uneTaille){
        this.saTaille = uneTaille;
    }

    public abstract List<CaseCarte> getForme(CaseCarte origine);

    public int getSaTaille() {
        return saTaille;
    }

    public void setSaTaille(int saTaille) {
        this.saTaille = saTaille;
    }

    public boolean isDansForme(CaseCarte origine, CaseCarte cible){
        for(CaseCarte cc : getForme(origine)){
            if(cc.getY() == cible.getY() && cc.getX() == cible.getX()){
                //Log.i("Forme", "cc X " + cc.getX() + " origine X " + origine.getX() + " cc Y " + cc.getY() + " origine Y " + origine.getY());
                return true;
            }
        }

        return false;
    }
}
