package virus.endtheboss.Modele.Formes;

import java.util.List;

import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.CaseVide;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class FormeEnLosange extends Forme {

    public FormeEnLosange(int taille){
        super(taille);
    }

    @Override
    public List<CaseCarte> getForme(CaseCarte origine) {
        for(int i = 1; i < saTaille; i++){
            forme.add(new CaseVide(origine.getX()+i, origine.getY()));
            forme.add(new CaseVide(origine.getX()-i, origine.getY()));
            forme.add(new CaseVide(origine.getX(), origine.getY()+i));
            forme.add(new CaseVide(origine.getX(), origine.getY()-i));
        }

        return forme;
    }
}
