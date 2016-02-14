package virus.endtheboss.Modele.Formes;

import java.util.List;

import virus.endtheboss.Modele.CaseCarte;

/**
 * Created by Quentin Gangler on 14/02/2016.
 */
public class FormeEnLosangeAvecOrigine extends FormeEnLosange {

    public FormeEnLosangeAvecOrigine(int taille){
        super(taille);
    }

    @Override
    public List<CaseCarte> getForme(CaseCarte origine){
        List<CaseCarte> forme = super.getForme(origine);
        forme.addAll(new FormeCase().getForme(origine));
        return forme;
    }
}
