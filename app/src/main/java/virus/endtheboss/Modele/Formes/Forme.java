package virus.endtheboss.Modele.Formes;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Modele.CaseCarte;

/**
 * Created by Valentin on 13/02/2016.
 */
public abstract class Forme {
    List<CaseCarte> forme;
    int saTaille;

    public Forme(int uneTaille){
        this.saTaille = uneTaille;
        forme = new ArrayList<>();
    }

    public abstract List<CaseCarte> getForme(CaseCarte origine);
}
