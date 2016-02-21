package virus.endtheboss.Modele.Capacites.Sbire;

import java.util.List;

import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeTous;
import virus.endtheboss.Modele.Personnages.Personnage;

/**
 * Created by Valentin on 18/02/2016.
 * Capacite soin boss d'un sbire
 */
public class SoinBoss extends Capacite{

    public SoinBoss(Carte uneCarte){
        super(uneCarte, "Tenez maitre !", new FormeCase(), new FormeTous());
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        List<Personnage> cibles = laCarte.getPersonnagesDansForme(sonImpact,uneCible);
        for(Personnage p : cibles){
            if (p instanceof Boss){
                p.soignerPersonnage(15);
            }
        }
    }
}
