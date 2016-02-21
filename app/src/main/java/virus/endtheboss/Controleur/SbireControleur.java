package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Capacites.Sbire.AttaqueSimple;
import virus.endtheboss.Modele.Capacites.Sbire.SoinBoss;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Personnages.Sbire;
import virus.endtheboss.Vue.GameSurface;

/**
 * Created by Valentin on 18/02/2016.
 * Classe permettant de créer une entité sbire avec des sorts et une vue avec barre de vie
 */
public class SbireControleur extends PersonnageControleur {
    public SbireControleur(Context mContext, GameSurface gs, Carte c, Sbire b) {
        super(mContext, gs,  c, b);

        p.ajouterCapacite(new AttaqueSimple((Sbire) p, c));
        p.ajouterCapacite(new SoinBoss(c));
        p.ajouterCapacite(new AttaqueSimple((Sbire) p, c));
        p.ajouterCapacite(new SoinBoss(c));
    }
}
