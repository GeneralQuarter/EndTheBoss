package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Capacites.Boss.FrappeDistance;
import virus.endtheboss.Modele.Capacites.Boss.FrappeZone;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Vue.GameSurface;

/**
 * Created by Quentin Gangler on 13/02/2016.
 * Classe permettant de créer une entité de boss avec sorts et vue avec barre de vie
 */
public class BossControleur extends PersonnageControleur {

    public BossControleur(Context mContext, GameSurface gs, Carte c, Boss b) {
        super(mContext, gs,  c, b);

        p.ajouterCapacite(new FrappeZone((Boss) p, c));
        p.ajouterCapacite(new FrappeDistance((Boss) p, c));
        p.ajouterCapacite(new FrappeZone((Boss) p, c));
        p.ajouterCapacite(new FrappeDistance((Boss) p, c));
    }
}
