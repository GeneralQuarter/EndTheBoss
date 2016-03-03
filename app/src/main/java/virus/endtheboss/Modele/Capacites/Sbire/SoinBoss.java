package virus.endtheboss.Modele.Capacites.Sbire;

import java.util.List;

import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeTous;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Sbire;

/**
 * Created by Valentin on 18/02/2016.
 * Capacite soin boss d'un sbire
 */
public class SoinBoss extends Capacite{

    private Sbire sonSbire;

    public SoinBoss(Sbire sbire, Carte uneCarte){
        super(uneCarte, "Tenez maitre !", new FormeCase(), new FormeTous());
        this.sonSbire=sbire;
    }

    @Override
    public void lancerSort(CaseCarte uneCible) {
        if (uneCible instanceof Boss){
            ((Boss) uneCible).soignerPersonnage(15);
            sonSbire.coupPersonnageSansArmure(10);
        }
    }
}
