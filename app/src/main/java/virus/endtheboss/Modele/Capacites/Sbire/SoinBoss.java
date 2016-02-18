package virus.endtheboss.Modele.Capacites.Sbire;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Modele.Boss;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeTous;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Modele.Sbire;

/**
 * Created by Valentin on 18/02/2016.
 */
public class SoinBoss extends Capacite{
    private Sbire sonSbire;

    public SoinBoss(Sbire unSbire, Carte uneCarte){
        super(uneCarte, "Tenez maitre !", new FormeCase(), new FormeTous());
        this.sonSbire=unSbire;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        List<Personnage> cibles = new ArrayList<>();
        cibles = laCarte.getPersonnagesDansForme(sonImpact,uneCible);
        for(Personnage p : cibles){
            if (p instanceof Boss){
                p.soignerPersonnage(15);
            }
        }
    }
}
