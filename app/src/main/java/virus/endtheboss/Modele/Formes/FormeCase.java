package virus.endtheboss.Modele.Formes;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Modele.CaseCarte;

/**
 * Created by Valentin on 13/02/2016.
 */
public class FormeCase extends Forme {

    public FormeCase(){
        super(0);
    }

    @Override
    public List<CaseCarte> getForme(CaseCarte origine) {
        List<CaseCarte> forme = new ArrayList<>();
        forme.add(origine);

        return forme;
    }
}
